using Nim.Core;
using Nim.Views;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Media;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Input;

namespace Nim.View_Models
{
    public class RulesViewModel : BaseViewModel
    {
        public new UserControl? ContentControlBinding { get; private set; }
        public string TitreText { get; set; }
        public string ContenuText { get; set; }
        public string CommencerBtnContent { get; set; }
        public ICommand CommencerBtnCommand { get; set; }
        private int Etape { get; set; }
        private int NbAllumettes;

        public RulesViewModel()
        {
            TitreText = "Règles du jeu";
            ContenuText = "Le jeu de Nim est un jeu de stratégie pour deux joueurs.\r\nLes joueurs retirent tour à tour 1, 2 ou 3 allumettes, le but étant d'être le dernier à retirer des allumettes.\r\nLe joueur qui retire la dernière allumette perd la partie.";
            CommencerBtnContent = "Suivant";
            Etape = 0;
            CommencerBtnCommand = new RelayCommand(CommencerBtnClick);            
        }

        private void CommencerBtnClick()
        {
            if (Etape == 0)
            {
                try
                {
                    TitreText = "Déroulement de la partie";
                    Random r = new();
                    NbAllumettes = r.Next(10, 21);
                    string joueurs = System.IO.File.ReadLines("nim.txt").Skip(0).Take(1).First();
                    string j1 = joueurs.Split(',')[0];
                    ContenuText = $"Le nombre d'allumettes pour cette partie sera de {NbAllumettes}.\r\nLe joueur qui commence est le joueur {j1}.\r\nLe joueur qui retire la dernière allumette perd la partie.";
                    CommencerBtnContent = "Commencer";
                    OnChanged(nameof(TitreText));
                    OnChanged(nameof(ContenuText));
                    OnChanged(nameof(CommencerBtnContent));
                    Etape++;
                } catch (Exception ex)
                {
                    MessageBox.Show(ex.Message);
                }
            }
            else if (Etape == 1)
            {
                ContentControlBinding = new GameView(NbAllumettes);
                OnChanged(nameof(ContentControlBinding));
            }
        }

    }
}
