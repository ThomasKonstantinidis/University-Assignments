import random
import re
times=0
score=0
the_words=['������','����','��������','�����','��������','����������','��������','��������','�����','����������']
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
      print "\n������!"
 
 
 
 
 
 
my_obj=my_game()
print "��� �������� ����, � ������� ��������� �� ���� �� ����, ����������� ��� ������ ���� ����. � ���� ������������� �� ����� _ _ _ _ _ _ _ (����� ������ ��� �� �������� ��� �����). �� �� ������ ������� ��� ����, ��� ������������� � ���� ��� ��������� ��� ���� (���� ����� �������), ��� �� �� ������ ��� �������, ���� ������������ ���� ���� ��� ������� ���� �������. �� � ������� ����� 9 ����� ���������� (������, ����, �. ����, �. ����, �. ����, �. ����, ��� ����, �����), ���� �������� ���� ������� ��� �����. �� �������� ��� ���� �� ����, ���� ��������."
win=0
mylist=[]
print "\n���� �� ����� ���: "
name=raw_input(">>>")

while(win==0):
  print "\n� ���� ����� � ����:"
  my_obj.words()
  print "\n",name,"���� ��� ������: "
  letter=raw_input(">>>")
  if not re.match("^[�-�]*$", letter):
    print "�����. ���� �������� �-�."
    times=times-1
  while(letter in mylist):
      print "\n�� ������ ���� ���� ���� �����."
      print "\n",name,"���� ��� ������: "
      letter=raw_input(">>>")
      if not re.match("^[�-�]*$", letter):
        print "�����. ���� �������� �-�."
  mylist.append(letter)
  if(letter in text):
    print "\n�� ������ ���� ���� ���� �����."
  elif(letter in word):
    for i in range(length):
      if(letter in word[i]):
        text = text[:i] + word[i] + text[i+1:]
  else:
    times+=1

  if("_" not in text):
    win=1
    my_obj.words()
    print "K�������!"
    score+=10
  if(times==9):
    win=1
    print "� ���� ����: %s" %(word)
  if(win==1):
      print "������ �� ���������� (0 ��� ��� � 1 ��� ���)."
      win=input()
      word=random.choice(the_words)
      mylist=[]
      text="_" *length
      times=0
  my_obj.hangman()


if(score>89):
  print "����� � ��������� ��� ��������. ��������� ��� �������. ����:", +score
elif(score>69):
  print "���� ����. ��������� ��� �������. ����:", +score
elif(score>49):
  print "��� �� ����� ��� ������. ��������� ��� �������. �� ���� ��� �����:", +score
elif(score>29):
  print "����� ���������������. ��������� ��� �������. ����:", +score
else:
  print "���������� ��������! ��������� ��� �������. �� ���� ��� �����: ", +score
