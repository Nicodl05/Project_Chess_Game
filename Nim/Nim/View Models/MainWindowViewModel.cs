using Nim.Models;
using Nim.Views;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Controls;
using System.Windows.Input;

namespace Nim.View_Models
{
    public class MainWindowViewModel : BaseViewModel
    {
        //Current side user control
        public new UserControl ContentControlBinding { get; private set; }
        public Musique musique = new();
        /// <summary>
        /// Represents Main Window view model with its controls.
        /// </summary>
        public MainWindowViewModel()
        {
            ContentControlBinding = new RulesView();
        }
    }
}
