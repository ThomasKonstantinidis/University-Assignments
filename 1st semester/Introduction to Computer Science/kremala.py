import random
import re
times=0
score=0
the_words=['óêõëïò','ãáôá','åëëçíéäá','ñïëïé','æùãñáöéá','ðïäïóöáéñï','áóèåíåéá','ìðáóêåôá','ï÷çìá','ðñùôåõïõóá']
word=random.choice(the_words)
length=len(word)
text="_"*length



 

class my_game:
   
  def words(self):
    print text

 
  def hangman(self):
    if(times==0):  
      print "|----|   "
      print "|        "
      print "|        "
      print "|        "
      print "|        "
      print "|        "
      print "|        "
 
    elif(times==1):  
      print "|----|   "
      print "|    O   "
      print "|        "
      print "|        "
      print "|        "
      print "|        "
      print "|        "
   
    elif(times==2):  
      print "|----|   "
      print "|    O   "
      print "|    |   "
      print "|        "
      print "|        "
      print "|        "
      print "|        "
   
    elif(times==3):  
      print "|----|   "
      print "|    O   "
      print "|    |\  "
      print "|        "
      print "|        "
      print "|        "
      print "|        "
   
    elif(times==4):  
      print "|----|   "
      print "|    O   "
      print "|   /|\  "
      print "|        "
      print "|        "
      print "|        "
      print "|        "
 
    elif(times==5):  
      print "|----|   "
      print "|    O   "
      print "|   /|\  "
      print "|    |   "
      print "|        "
      print "|        "
      print "|        "
 
    elif(times==6):  
      print "|----|   "
      print "|    O   "
      print "|   /|\  "
      print "|    |   "
      print "|     \_ "
      print "|        "
      print "|        "

    elif(times==7):  
      print "|----|   "
      print "|    O   "
      print "|   /|\  "
      print "|    |   "
      print "|   _/\_ "
      print "|        " 
      print "|        "

    elif(times==8):  
      print "|----|   "
      print "|    O   "
      print "|   /|\  "
      print "|    |   "
      print "|   _/\_ "
      print "|   #### "
      print "|        "

    elif(times==9):  
      print "|----|   "
      print "|    O   "
      print "|   /|\  "
      print "|    |   "
      print "|   _/\_ "
      print "|   #### "
      print "|   fire "
      print "\n¸÷áóåò!"
 
 
 
 
 
 
my_obj=my_game()
print "Óôï ðáé÷íßäé áõôü, ï ðáß÷ôçò ðñïóðáèåß íá âñåé ôç ëÝîç, ìáíôåýïíôáò Ýíá ãñÜììá êÜèå öïñÜ. Ç ëÝîç ðáñïõóéÜæåôáé óå ìïñöÞ _ _ _ _ _ _ _ (ôüóåò ðÜõëåò üóá ôá ãñÜììáôá ôçò ëÝîçò). Áí ôï ãñÜììá õðÜñ÷åé óôç ëÝîç, ôïõ õðïäåéêíýåôáé ç èÝóç ôïõ ãñÜììáôïò óôç ëÝîç (üóåò öïñÝò õðÜñ÷åé), åíþ áí ôï ãñÜììá äåí õðÜñ÷åé, ôüôå ó÷çìáôßæåôáé óéãÜ óéãÜ ìßá öéãïýñá óôçí êñåìÜëá. Áí ï ðáß÷ôçò äþóåé 9 ëÜèïò áðáíôÞóåéò (êåöÜëé, óþìá, ä. ÷Ýñé, á. ÷Ýñé, ä. ðüäé, á. ðüäé, äýï îýëá, öùôéÜ), ôüôå êáßãåôáé óôçí êñåìÜëá êáé ÷Üíåé. Áí ðñïëÜâåé êáé âñåé ôç ëÝîç, ôüôå êåñäßæåé."
win=0
mylist=[]
print "\nÄþóå ôï üíïìá óïõ: "
name=raw_input(">>>")

while(win==0):
  print "\nÇ ëÝîç åßíáé ç åîÞò:"
  my_obj.words()
  print "\n",name,"äþóå Ýíá ãñÜììá: "
  letter=raw_input(">>>")
  if not re.match("^[á-ù]*$", letter):
    print "ËÜèïò. Ìüíï ãñÜììáôá á-ù."
    times=times-1
  while(letter in mylist):
      print "\nÔï ãñÜììá áõôü Ý÷åé îáíÜ äùèåß."
      print "\n",name,"äþóå Ýíá ãñÜììá: "
      letter=raw_input(">>>")
      if not re.match("^[á-ù]*$", letter):
        print "ËÜèïò. Ìüíï ãñÜììáôá á-ù."
  mylist.append(letter)
  if(letter in text):
    print "\nÔï ãñÜììá áõôü Ý÷åé îáíÜ äùèåß."
  elif(letter in word):
    for i in range(length):
      if(letter in word[i]):
        text = text[:i] + word[i] + text[i+1:]
  else:
    times+=1

  if("_" not in text):
    win=1
    my_obj.words()
    print "KÝñäéóåò!"
    score+=10
  if(times==9):
    win=1
    print "Ç ëÝîç Þôáí: %s" %(word)
  if(win==1):
      print "ÈÝëåéò íá óõíå÷ßóåéò (0 ãéá ÍÁÉ ç 1 ãéá Ï×É)."
      win=input()
      word=random.choice(the_words)
      mylist=[]
      text="_" *length
      times=0
  my_obj.hangman()


if(score>89):
  print "Åßóáé ï êõñßáñ÷ïò ôçò êñåìÜëáò. Åõ÷áñéóôþ ðïõ Ýðáéîåò. Óêïñ:", +score
elif(score>69):
  print "Ðïëý êáëÜ. Åõ÷áñéóôþ ðïõ Ýðáéîåò. Óêïñ:", +score
elif(score>49):
  print "Äåí ôá ðÞãåò êáé Üó÷çìá. Åõ÷áñéóôþ ðïõ Ýðáéîåò. Ôï óêïñ óïõ åéíáé:", +score
elif(score>29):
  print "Åßóáé ìéóïêñåìáóìÝíïò. Åõ÷áñéóôþ ðïõ Ýðáéîåò. Óêïñ:", +score
else:
  print "×ñåéÜæåóáé åîÜóêçóç! Åõ÷áñéóôþ ðïõ Ýðáéîåò. Ôï óêïñ óïõ åéíáé: ", +score
