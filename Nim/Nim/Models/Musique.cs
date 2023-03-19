using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;

namespace Nim.Models
{
    public class Musique
    {
        public Musique()
        {
            new System.Threading.Thread(() =>
            {
                var c = new System.Windows.Media.MediaPlayer();
                c.Open(new System.Uri("Assets/Musique.wav", UriKind.Relative));
                c.Play();
                Task.Delay(1000).Wait();
            }).Start();
        }
    }
}
