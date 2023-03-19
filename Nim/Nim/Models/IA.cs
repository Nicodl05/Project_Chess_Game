using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Nim.Models
{
    public class IA : Joueur
    {
        public int Numero { get; set; }
        public IA(Joueur joueur, int num) : base(joueur.Nom, joueur.Couleur)
        {
            Numero = num;
        }

        public int Jouer(int nbAllumettesRestantes)
        {
            if (nbAllumettesRestantes == 1 || nbAllumettesRestantes == 2)
                return 1;
            else if (nbAllumettesRestantes == 3)
                return 2;
            else
            {
                return (nbAllumettesRestantes % 4) switch
                {
                    0 => 3,
                    1 => 3,
                    2 => 1,
                    3 => 2,
                    _ => 3,
                };
            }
        }
    }
}
