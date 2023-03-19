using Nim.Core;
using Nim.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Media;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Controls;
using System.Windows.Input;

namespace Nim.View_Models
{
    public class EndViewModel : BaseViewModel
    {
        public new UserControl? ContentControlBinding { get; private set; }
        public string Winner { get; set; }
        public System.Windows.Media.SolidColorBrush CouleurVainqueur { get; set; }
        public ICommand FinBtnCommand { get; set; }
        private string Couleur;

        public EndViewModel(Joueur vainqueur)
        {
            Winner = vainqueur.Nom;
            SoundPlayer sound;
            switch (vainqueur.Couleur)
            {
                case "Blanc":
                    CouleurVainqueur = System.Windows.Media.Brushes.White;
                    sound = new SoundPlayer("Assets/VictoireBlancs.wav");
                    break;
                case "Noir":
                    sound = new SoundPlayer("Assets/VictoireNoirs.wav");
                    CouleurVainqueur = System.Windows.Media.Brushes.Black;
                    break;
                default:
                    sound = new SoundPlayer();
                    CouleurVainqueur = System.Windows.Media.Brushes.Red;
                    break;
            }
            sound.Load();
            sound.Play();
            Couleur = vainqueur.Couleur;
            FinBtnCommand = new RelayCommand(FinBtnClick);
        }

        private void FinBtnClick()
        {
            System.IO.File.AppendAllText("nim.txt", $"{Couleur}");
            System.Windows.Application.Current.Shutdown();
        }
    }
}
