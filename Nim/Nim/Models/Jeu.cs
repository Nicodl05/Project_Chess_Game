using System;
using System.Collections.Generic;
using System.Linq;
using System.Media;
using System.Text;
using System.Threading.Tasks;
using System.Windows;

namespace Nim.Models
{
    public class Jeu
    {
        public List<Joueur> Joueurs { get; private set; }
        public int NbAllumettes { get; private set; }
        public int NbAllumettesRestantes { get; set; }
        public List<int> Allumettes { get; set; }
        public int Tour { get; private set; }

        public Jeu(int nbAllumettes)
        {
            Joueurs = new List<Joueur>();
            string[] fichier = System.IO.File.ReadLines("nim.txt").ToArray();
            Joueurs.Add(new Joueur(fichier[0].Split(',')[0], fichier[1].Split(',')[0]));
            Joueurs.Add(new Joueur(fichier[0].Split(',')[1], fichier[1].Split(',')[1]));
            Allumettes = new List<int>();
            for (int i = 0; i < nbAllumettes; i++)
            {
                Allumettes.Add(i);
            }
            Tour = 0;
            /*string test = "";
            foreach (var item in Joueurs)
            {
                test += item + "\n";
            }
            NbAllumettesRestantes = NbAllumettes;
            MessageBox.Show(test);*/
            SoundPlayer sound = new("Assets/Bonnechance.wav");
            sound.Load();
            sound.Play();
        }

        public bool Jouer(int nbRetire)
        {
            if (nbRetire > 0 && nbRetire <= 3)
            {
                NbAllumettesRestantes -= nbRetire;
                Allumettes.RemoveRange(0, nbRetire);
                return Allumettes.Count != 0;
            }
            else
            {
                throw new Exception("Problème lors du tour.");
            }

        }

    }
}
