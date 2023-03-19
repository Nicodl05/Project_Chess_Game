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

namespace Nim.Views
{
    /// <summary>
    /// Logique d'interaction pour EndView.xaml
    /// </summary>
    public partial class EndView : UserControl
    {
        public EndView(Joueur vainqueur)
        {
            InitializeComponent();
            this.DataContext = new EndViewModel(vainqueur);
        }
    }
}
