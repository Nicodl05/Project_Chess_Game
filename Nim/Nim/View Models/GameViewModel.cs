using Nim.Core;
using Nim.Models;
using Nim.Views;
using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;

namespace Nim.View_Models
{
    public class GameViewModel : BaseViewModel
    {
        public new UserControl? ContentControlBinding { get; private set; }
        private Jeu Jeu;
        public string QuiJoueTexte { get; set; }
        public string ResteTexte { get; set; }
        public bool TourUtilisateur { get; set; }
        public ObservableCollection<Border> Allumettes { get; set; }
        public int Tour { get; set; }
        private IA Ia = new(new("", ""), 0);

        // Properties to track the selected allumettes and the number of allumettes removed in the current turn
        private List<Border> selectedAllumettes = new();
        private int nbAllumettesRemoved = 0;

        public GameViewModel(int nbAllumettes)
        {
            Jeu = new(nbAllumettes);
            int cpt = 0;
            foreach (var joueur in Jeu.Joueurs)
            {
                if (joueur.Nom == "Ordinateur")
                {
                    Ia = new(joueur, cpt);
                    break;
                }
                cpt++;
            }
            QuiJoueTexte = Jeu.Joueurs[0].Nom;
            ResteTexte = $"Allumettes restantes : {nbAllumettes}";

            // Populate Allumettes collection with Border
            Allumettes = new ObservableCollection<Border>();
            for (int i = 0; i < nbAllumettes; i++)
            {
                Border allumette = new()
                {
                    Width = 50,
                    Height = 200
                };
                Allumettes.Add(allumette);
            }
            Tour = 0;
            if (Ia.Numero == 0)
            {
                TourUtilisateur = false;
                TourIa();
            }
            else
            {
                TourUtilisateur = true;
            }
        }

        // Event handler for the MouseLeftButtonUp event of the allumette Border elements
        public void Allumette_MouseLeftButtonUp(object sender, MouseButtonEventArgs e)
        {
            if (TourUtilisateur)
            {
                Border selectedAllumette = (Border)sender;

                // Check if the allumette is already selected
                if (selectedAllumettes.Contains(selectedAllumette))
                {
                    // Deselect the allumette
                    selectedAllumette.Opacity = 1;
                    selectedAllumettes.Remove(selectedAllumette);
                    nbAllumettesRemoved--;
                }
                else if (nbAllumettesRemoved < 3)
                {
                    // Select the allumette

                    selectedAllumette.Opacity = 0.5;
                    selectedAllumettes.Add(selectedAllumette);
                    nbAllumettesRemoved++;
                }
            }
        }

        // Command to remove the selected allumettes and end the turn
        public ICommand EndTurnCommand => new RelayCommand(() =>
        {
            // Check if the user has removed at least one allumette this turn
            if (nbAllumettesRemoved <= 0)
            {
                MessageBox.Show("Vous devez retirer au moins une allumette pour terminer votre tour.");
                return;
            }

            // Check if the user has removed too many allumettes this turn
            if (nbAllumettesRemoved > 3)
            {
                MessageBox.Show("Vous ne pouvez pas retirer plus de trois allumettes par tour.");
                return;
            }

            // Check if it remains enough allumettes to continue the game
            if (Allumettes.Count - nbAllumettesRemoved < 0)
            {
                MessageBox.Show("Vous ne pouvez pas retirer plus d'allumettes qu'il y en a sur le plateau.");
                return;
            }

            // Remove the selected allumettes
            foreach (var allumette in Allumettes)
            {
                allumette.Opacity = 1;
            }

            foreach (var allumette in selectedAllumettes)
            {
                allumette.Opacity = 1;
            }

            for (int i = 0; i < nbAllumettesRemoved; i++)
            {
                Allumettes.RemoveAt(0);
            }

            if (Jeu.Jouer(nbAllumettesRemoved))
            {
                // Update the number of allumettes remaining

                selectedAllumettes.Clear();
                nbAllumettesRemoved = 0;
                Tour++;
                QuiJoueTexte = Jeu.Joueurs[Tour%2].Nom;
                ResteTexte = $"Allumettes restantes : {Allumettes.Count}";
                TourUtilisateur = false;
                OnChanged(nameof(TourUtilisateur));
                OnChanged(nameof(QuiJoueTexte));
                OnChanged(nameof(ResteTexte));
                OnChanged(nameof(Allumettes));
                TourIa();
            }
            else
            {
                //MessageBox.Show($"Fin de partie !\nGagnant : {Jeu.Joueurs[(Tour + 1) % 2].Nom}({Jeu.Joueurs[(Tour + 1) % 2].Couleur})");
                ContentControlBinding = new EndView(Jeu.Joueurs[(Tour + 1) % 2]);
                OnChanged(nameof(ContentControlBinding));
            }            
        });

        private async void TourIa()
        {
            await Task.Delay(400);
            int nbAllumettes = Ia.Jouer(Allumettes.Count);
            //MessageBox.Show($"L'ordinateur a retiré {nbAllumettes} allumettes.");
            for (int i = 0; i < nbAllumettes; i++)
            {
                await Task.Delay(500);
                Allumettes.RemoveAt(0);
            }
            if (Jeu.Jouer(nbAllumettes))
            {
                selectedAllumettes.Clear();
                nbAllumettesRemoved = 0;
                Tour++;
                QuiJoueTexte = Jeu.Joueurs[Tour % 2].Nom;
                ResteTexte = $"Allumettes restantes : {Allumettes.Count}";
                TourUtilisateur = true;
                OnChanged(nameof(TourUtilisateur));
                OnChanged(nameof(QuiJoueTexte));
                OnChanged(nameof(ResteTexte));
                OnChanged(nameof(Allumettes));
            }
            else
            {
                //MessageBox.Show($"Fin de partie !\nGagnant : {Jeu.Joueurs[(Tour + 1) % 2].Nom}({Jeu.Joueurs[(Tour + 1) % 2].Couleur})");
                ContentControlBinding = new EndView(Jeu.Joueurs[(Tour + 1) % 2]);
                OnChanged(nameof(ContentControlBinding));
            }
        }

    }
}
