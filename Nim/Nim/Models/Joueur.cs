using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Nim.Models
{
    public class Joueur
    {
        public string Nom { get; set; }
        public string Couleur { get; set; }

        public Joueur(string nom, string couleur)
        {
            Nom = nom;
            Couleur = couleur;
        }

        public override string ToString()
        {
            return $"{Nom} ({Couleur})";
        }
    }
}
