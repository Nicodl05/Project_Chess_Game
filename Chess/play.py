import time
from keras.models import load_model
import cv2
import numpy as np
from random import choice

try:
    start_time = time.time()
    window_closed = False

    string_file = []

    file = open("nim.txt", "r")
    try:
        string_file = file.readlines()
    finally:
        file.close()

    cleaned_list = [word.strip() for line in string_file for word in line.split(',')] #cleaned_list[0] = nom, cleaned_list[1] = ordi ou joueur
    if cleaned_list[1] == 'Ordinateur':
        player_attack = True


    # définir le gestionnaire d'événements pour la fenêtre
    def on_close_window(event, x, y, flags, param):
        global window_closed
        if event == cv2.WINDOW_CLOSE:
            window_closed = True



    REV_CLASS_MAP = {
        0: "rock",
        1: "paper",
        2: "scissors",
        3: "none"
    }

    def mapper(val):
        return REV_CLASS_MAP[val]


    def calculate_winner(move1, move2):
        if move1 == move2:
            return "Tie"

        if move1 == "rock":
            if move2 == "scissors":
                return "User"
            if move2 == "paper":
                return "Computer"

        if move1 == "paper":
            if move2 == "rock":
                return "User"
            if move2 == "scissors":
                return "Computer"

        if move1 == "scissors":
            if move2 == "paper":
                return "User"
            if move2 == "rock":
                return "Computer"

    model = load_model("rock-paper-scissors-model.h5")

    cap = cv2.VideoCapture(0)
    cap.set(cv2.CAP_PROP_FRAME_WIDTH, 1200)
    cap.set(cv2.CAP_PROP_FRAME_HEIGHT, 800)
    cv2.namedWindow("Rock Paper Scissors")
    cv2.setMouseCallback("Rock Paper Scissors", on_close_window)

    #Initialisation des variables
    prev_move = None
    nbGame=0
    winner1 =""
    winner2 =""
    winner3 =""
    end=False
    nbWinUser=0
    nbWinComputer=0
    textWinner=""

    while True:
        ret, frame = cap.read()

        if not ret:
            continue


        # Créer un masque alpha pour l'image cible
        alpha_mask = np.zeros(frame.shape[:2], dtype=np.uint8)
        alpha_mask[100:500, 800:1200] = 255  # Région où l'image sera affichée

        # Ajouter le canal alpha à l'image cible
        frame_rgba = cv2.cvtColor(frame, cv2.COLOR_BGR2BGRA)

        # Appliquer le masque alpha à l'image cible
        frame_rgba[:, :, 3] = alpha_mask

        # rectangle du joueur
        cv2.rectangle(frame_rgba, (100, 100), (500, 500), (255, 255, 255), 2)
        # rectangle de l'ordinateur
        cv2.rectangle(frame_rgba, (800, 100), (1200, 500), (255, 255, 255), 2)
        # rectangle des résultats
        cv2.rectangle(frame_rgba, (100, 550), (1200, 700), (128, 0, 0), -1)

        # Extrait la region du rectangle utilisateur
        roi = frame_rgba[100:500, 100:500]
        #roi = 0;
        img = cv2.cvtColor(roi, cv2.COLOR_BGR2RGB)
        img = cv2.resize(img, (227, 227))


        # predit le geste
        pred = model.predict(np.array([img]))
        move_code = np.argmax(pred[0])
        user_move_name = mapper(move_code)

        end_time = time.time()
        elapsed_time = end_time - start_time

        # prédit le gagnant
        if prev_move != user_move_name and elapsed_time>2 and winner3=="":
            if user_move_name != "none":
                computer_move_name = choice(['rock', 'paper', 'scissors'])


                if nbGame==0:
                    winner1 = calculate_winner(user_move_name, computer_move_name)
                    if winner1=='User':
                        nbWinUser+=1
                    elif winner1=='Computer':
                        nbWinComputer+=1

                elif nbGame==1:
                    winner2 = calculate_winner(user_move_name, computer_move_name)
                    if winner2=='User':
                        nbWinUser+=1
                    elif winner2=='Computer':
                        nbWinComputer+=1
                else:
                    winner3 = calculate_winner(user_move_name, computer_move_name)
                    if winner3=='User':
                        nbWinUser+=1
                    elif winner3=='Computer':
                        nbWinComputer+=1


                start_time=time.time()
                nbGame+=1

            else:
                computer_move_name = "none"


        prev_move = user_move_name

        # Affiche les informations
        font = cv2.FONT_HERSHEY_SIMPLEX
        cv2.putText(frame_rgba, "Your Move: " + user_move_name,
                    (50, 50), font, 1.2, (255, 255, 255), 2, cv2.LINE_AA)
        cv2.putText(frame_rgba, "Computer's Move: " + computer_move_name,
                    (750, 50), font, 1.2, (255, 255, 255), 2, cv2.LINE_AA)

        cv2.putText(frame_rgba, "Round 1: " + winner1,
                        (120, 580), font, 1, (255, 255, 255), 4, cv2.LINE_AA)

        cv2.putText(frame_rgba, "Round 2: " + winner2,
                        (120, 630), font, 1, (255, 255, 255), 4, cv2.LINE_AA)
        cv2.putText(frame_rgba, "Round 3: " + winner3,
                        (120, 680), font, 1, (255, 255, 255), 4, cv2.LINE_AA)

        if winner1!="" and winner2!="" and winner3!="":
            if nbWinUser>nbWinComputer:
                cv2.putText(frame_rgba, "Congratulation !!",
                            (600, 635), font, 2, (0, 255, 0), 4, cv2.LINE_AA)
                textWinner="Joueur"
            elif nbWinUser==nbWinComputer:
                cv2.putText(frame_rgba, "Tie, too bad...",
                            (600, 635), font, 2, (0, 0, 255), 4, cv2.LINE_AA)
                textWinner="Ordinateur"
            else:
                cv2.putText(frame_rgba, "Game over",
                            (600, 635), font, 2, (0, 0, 255), 4, cv2.LINE_AA)
                textWinner="Ordinateur"


        if computer_move_name != "none":
            icon = cv2.imread(
                "images/{}.png".format(computer_move_name),cv2.IMREAD_UNCHANGED)

            icon = cv2.resize(icon, (400, 400))

            alpha_s = icon[:, :, 3] / 255.0  # Normaliser les valeurs alpha
            alpha_l = 1.0 - alpha_s
            for c in range(0, 3):
                frame_rgba[100:500, 800:1200, c] = (alpha_s * icon[:, :, c] +
                                                    alpha_l * frame_rgba[100:500, 800:1200, c])


        cv2.imshow("Rock Paper Scissors", frame_rgba)

        # Fermeture du jeu
        k = cv2.waitKey(10)
        k2 =cv2.waitKey(10)
        if (k == ord('q')) or window_closed:
            break


    winner = ''
    if textWinner==cleaned_list[0]:
        winner = cleaned_list[2]
    elif textWinner==cleaned_list[1]:
        winner = cleaned_list[3]
    else:
        if cleaned_list[0]=="Ordinateur":
            winner = cleaned_list[3]
        else:
            winner = cleaned_list[2]

    file = open("nim.txt", "a")

    try:
        file.write("\n"+winner)
    finally:
        file.close()



    cap.release()
    cv2.destroyAllWindows()
except:
    file = open("nim.txt", "a")

    try:
        file.write("Blanc")
    finally:
        file.close()
