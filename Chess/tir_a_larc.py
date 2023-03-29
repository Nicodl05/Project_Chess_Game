import cv2
from cvzone.HandTrackingModule import HandDetector
import random


cap = cv2.VideoCapture(0)
detector = HandDetector(detectionCon=0.8, maxHands=2)
image_flipped = cv2.imread(cv2.samples.findFile("background.png"))
background = cv2.imread(cv2.samples.findFile("background.png"))
background2 = cv2.imread(cv2.samples.findFile("background2.png"))
bienvenue = cv2.imread(cv2.samples.findFile("bienvenue.png"))

circles=[[30,10],[63,9],[95,8],[125,7],[155,6],[190,5],[220,4],[250,3],[280,2],[310,1]]
center =(0,0)
touched = False
shot = False
end = False
score = 0
H1 = False
H2 = False
player_attack = True

string_file = []

file = open("nim.txt", "r")
try:
    string_file = file.readlines()
finally:
    file.close()

cleaned_list = [word.strip() for line in string_file for word in line.split(',')]
if cleaned_list[1] == 'Ordinateur':
    player_attack = True

cv2.imshow("Cible", bienvenue)
cv2.waitKey()

while end == False:
    success, img = cap.read()
    hands, img = detector.findHands(img)
    image = background.copy()

    if hands:
        if len(hands) == 2:
            hand1 = hands[0]
            handType1 = hand1['type']

            hand2 = hands[1]
            handType2 = hand2['type']
            if handType1 == "Right":
                H1 = True
            if handType2 == "Left":
                H2 = True
                centerPoint2 = hand2['center']
                center = (centerPoint2[0]+620, centerPoint2[1]+270)
                cv2.line(image, (center[0] - 5, center[1] - 5), (center[0] + 5, center[1] + 5), (255, 50, 0), 4)
                cv2.line(image, (center[0] + 5, center[1] - 5), (center[0] - 5, center[1] + 5), (255, 50, 0), 4)

            if detector.fingersUp(hand1) == [1, 1, 1, 1, 1] and detector.fingersUp(hand2) != [0, 0, 0, 0, 0] and detector.fingersUp(hand2) != [1, 1, 1, 1, 1]:
                score = 0
                shot = True
                cv2.line(image, (center[0] - 10, center[1] - 10), (center[0] + 10, center[1] + 10), (255, 50, 0), 6)
                cv2.line(image, (center[0] + 10, center[1] - 10), (center[0] - 10, center[1] + 10), (255, 50, 0), 6)
                for circle in circles:
                    if center[0] >= 960-circle[0] and center[0] <= 960+circle[0] and center[1] >= 540-circle[0] and center[1] <= 540+circle[0]:
                        score = circle[1]
                        touched = True
                        shot = False
                        break

    image_flipped = cv2.flip(image, 1)

    if H1:
        cv2.putText(image_flipped, "Main droite detectee", (1710, 20), cv2.FONT_HERSHEY_PLAIN, 1, (255, 255, 255), 1)
    if H2:
        cv2.putText(image_flipped, "Main gauche detectee", (1710, 35), cv2.FONT_HERSHEY_PLAIN, 1, (255, 255, 255), 1)
    if touched:
        cv2.putText(image_flipped, "Cible touchee: " + str(score), (830, 900), cv2.FONT_HERSHEY_PLAIN, 2, (255, 255, 255), 2)
    elif shot:
        cv2.putText(image_flipped, "Cible manquee: " + str(score), (810, 900), cv2.FONT_HERSHEY_PLAIN, 2, (255, 255, 255), 2)
    if player_attack:
        cv2.putText(image_flipped, cleaned_list[0], (5, 30), cv2.FONT_HERSHEY_PLAIN, 2, (255, 255, 255), 2)
    else:
        cv2.putText(image_flipped, cleaned_list[1], (5, 30), cv2.FONT_HERSHEY_PLAIN, 2, (255, 255, 255), 2)
    cv2.imshow("Cible", image_flipped)
    ##cv2.imshow("img", img)
    if touched or shot:
        cv2.waitKey(2000)
        end = True
    cv2.waitKey(1)

cap.release()
if player_attack == 'Ordinateur':
    score_Ordinateur = random.randint(5,10)
else:
    score_Ordinateur = random.randint(0,10)

winner = ''
if score > score_Ordinateur:
    cv2.putText(background2, "BRAVO !", (800, 200), cv2.FONT_HERSHEY_SIMPLEX, 3, (255, 255, 255), 5)
    if player_attack:
        winner = cleaned_list[2]
    else:
        winner = cleaned_list[3]
elif score < score_Ordinateur:
    cv2.putText(background2, "DOMMAGE !", (765, 200), cv2.FONT_HERSHEY_SIMPLEX, 3, (255, 255, 255), 5)
    if player_attack:
        winner = cleaned_list[3]
    else:
        winner = cleaned_list[2]
elif score == score_Ordinateur:
    if player_attack:
        winner = cleaned_list[2]
        cv2.putText(background2, "BRAVO !", (800, 200), cv2.FONT_HERSHEY_SIMPLEX, 3, (255, 255, 255), 5)
    else:
        winner = cleaned_list[3]
        cv2.putText(background2, "DOMMAGE !", (765, 200), cv2.FONT_HERSHEY_SIMPLEX, 3, (255, 255, 255), 5)

cv2.putText(background2, "Score joueur: " + str(score), (735, 500), cv2.FONT_HERSHEY_SIMPLEX, 2, (255, 255, 255), 3)
cv2.putText(background2, "Score Ordinateur: " + str(score_Ordinateur), (775, 600), cv2.FONT_HERSHEY_SIMPLEX, 2, (255, 255, 255), 3)
cv2.imshow("Cible", background2)
cv2.waitKey(5000)
cv2.destroyAllWindows()

file = open("nim.txt", "a")

try:
    file.write("\n"+winner)
finally:
    file.close()