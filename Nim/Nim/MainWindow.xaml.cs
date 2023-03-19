using Nim.Models;
using Nim.View_Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace Nim
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        public MainWindow()
        {
            InitializeComponent();
            this.DataContext = new MainWindowViewModel();
        }

        #region Custom Titlebar
        //Source:
        //https://www.youtube.com/watch?v=V9DkvcT27WI
        private void DragBorder(object sender, MouseButtonEventArgs e)
        {
            if (e.LeftButton == MouseButtonState.Pressed)
                DragMove();
        }

        private void MinimizeWindow(object sender, RoutedEventArgs e)
        {
            Application.Current.MainWindow.WindowState = WindowState.Minimized;
        }

        private void MaximizeWindow(object sender, RoutedEventArgs e)
        {
            if (Application.Current.MainWindow.WindowState != WindowState.Maximized)
                Application.Current.MainWindow.WindowState = WindowState.Maximized;
            else
                Application.Current.MainWindow.WindowState = WindowState.Normal;
        }

        private void CloseWindow(object sender, RoutedEventArgs e)
        {
            MessageBoxResult result = MessageBox.Show("Voulez-vous vraiment quitter le jeu ?\nQuitter le jeu vous fera automatiquement perdre la partie.", "Abandonner ?", MessageBoxButton.YesNo, MessageBoxImage.Question);
            if (result == MessageBoxResult.Yes)
            {
                List<Joueur> Joueurs = new List<Joueur>();
                string[] fichier = System.IO.File.ReadLines("nim.txt").ToArray();
                Joueurs.Add(new Joueur(fichier[0].Split(',')[0], fichier[1].Split(',')[0]));
                Joueurs.Add(new Joueur(fichier[0].Split(',')[1], fichier[1].Split(',')[1]));
                foreach (Joueur j in Joueurs)
                {
                    if (j.Nom == "Ordinateur")
                        System.IO.File.AppendAllText("nim.txt", $"{j.Couleur}");
                }
                Application.Current.Shutdown();
            }
        }
        #endregion
    }
}
