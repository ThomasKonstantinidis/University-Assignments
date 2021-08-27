import random
import re
times=0
score=0
the_words=['σκυλος','γατα','ελληνιδα','ρολοι','ζωγραφια','ποδοσφαιρο','ασθενεια','μπασκετα','οχημα','πρωτευουσα']
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
      print "\nΈχασες!"
 
 
 
 
 
 
my_obj=my_game()
print "Στο παιχνίδι αυτό, ο παίχτης προσπαθεί να βρει τη λέξη, μαντεύοντας ένα γράμμα κάθε φορά. Η λέξη παρουσιάζεται σε μορφή _ _ _ _ _ _ _ (τόσες πάυλες όσα τα γράμματα της λέξης). Αν το γράμμα υπάρχει στη λέξη, του υποδεικνύεται η θέση του γράμματος στη λέξη (όσες φορές υπάρχει), ενώ αν το γράμμα δεν υπάρχει, τότε σχηματίζεται σιγά σιγά μία φιγούρα στην κρεμάλα. Αν ο παίχτης δώσει 9 λάθος απαντήσεις (κεφάλι, σώμα, δ. χέρι, α. χέρι, δ. πόδι, α. πόδι, δύο ξύλα, φωτιά), τότε καίγεται στην κρεμάλα και χάνει. Αν προλάβει και βρει τη λέξη, τότε κερδίζει."
win=0
mylist=[]
print "\nΔώσε το όνομα σου: "
name=raw_input(">>>")

while(win==0):
  print "\nΗ λέξη είναι η εξής:"
  my_obj.words()
  print "\n",name,"δώσε ένα γράμμα: "
  letter=raw_input(">>>")
  if not re.match("^[α-ω]*$", letter):
    print "Λάθος. Μόνο γράμματα α-ω."
    times=times-1
  while(letter in mylist):
      print "\nΤο γράμμα αυτό έχει ξανά δωθεί."
      print "\n",name,"δώσε ένα γράμμα: "
      letter=raw_input(">>>")
      if not re.match("^[α-ω]*$", letter):
        print "Λάθος. Μόνο γράμματα α-ω."
  mylist.append(letter)
  if(letter in text):
    print "\nΤο γράμμα αυτό έχει ξανά δωθεί."
  elif(letter in word):
    for i in range(length):
      if(letter in word[i]):
        text = text[:i] + word[i] + text[i+1:]
  else:
    times+=1

  if("_" not in text):
    win=1
    my_obj.words()
    print "Kέρδισες!"
    score+=10
  if(times==9):
    win=1
    print "Η λέξη ήταν: %s" %(word)
  if(win==1):
      print "Θέλεις να συνεχίσεις (0 για ΝΑΙ η 1 για ΟΧΙ)."
      win=input()
      word=random.choice(the_words)
      mylist=[]
      text="_" *length
      times=0
  my_obj.hangman()


if(score>89):
  print "Είσαι ο κυρίαρχος της κρεμάλας. Ευχαριστώ που έπαιξες. Σκορ:", +score
elif(score>69):
  print "Πολύ καλά. Ευχαριστώ που έπαιξες. Σκορ:", +score
elif(score>49):
  print "Δεν τα πήγες και άσχημα. Ευχαριστώ που έπαιξες. Το σκορ σου ειναι:", +score
elif(score>29):
  print "Είσαι μισοκρεμασμένος. Ευχαριστώ που έπαιξες. Σκορ:", +score
else:
  print "Χρειάζεσαι εξάσκηση! Ευχαριστώ που έπαιξες. Το σκορ σου ειναι: ", +score
