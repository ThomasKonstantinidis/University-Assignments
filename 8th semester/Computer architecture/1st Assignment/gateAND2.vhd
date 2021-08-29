/**************Team******************/
/*1.ANGELIKI ALESTA [3130251]********/
/*2.THOMAS KONSTANTINIDIS [3160074]**/
/************************************/

LIBRARY ieee;
Use ieee.std_logic_1164.all;

Entity gateAND2 is

port(in1,in2: IN std_logic;
	  out1: OUT std_logic);
	  
end gateAND2;

ARCHITECTURE func of gateAND2 is 
BEGIN    
	out1 <= in1 AND in2;
END func;