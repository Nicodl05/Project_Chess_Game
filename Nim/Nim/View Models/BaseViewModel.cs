using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Controls;
using System.Windows.Input;

namespace Nim.View_Models
{
    /// <summary>
    /// Represents the base of all the view models with their shared controls
    /// </summary>
    public class BaseViewModel : INotifyPropertyChanged
    {
        public UserControl? ContentControlBinding { get; private set; } //Current user control
        public event PropertyChangedEventHandler? PropertyChanged;
        public void ChangeView(object parameter)
        {
            ContentControlBinding = (UserControl)parameter;
            OnChanged(nameof(ContentControlBinding));
        }
        /// <summary>
        /// Occurs when a property value changes.
        /// </summary>
        /// <param name="name">Name of the value</param>
        public void OnChanged(string name)
        {
#pragma warning disable CS8602 // Déréférencement d'une éventuelle référence null.
            PropertyChanged(this, new PropertyChangedEventArgs(name));
#pragma warning restore CS8602 // Déréférencement d'une éventuelle référence null.
        }

        
    }
}
