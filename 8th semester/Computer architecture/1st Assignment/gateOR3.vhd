/**************Team******************/
/*1.ANGELIKI ALESTA [3130251]********/
/*2.THOMAS KONSTANTINIDIS [3160074]**/
/************************************/

LIBRARY ieee;
Use ieee.std_logic_1164.all;

Entity gateOR3 is

port(in1, in2, in3: IN std_logic;
	  out1: OUT std_logic);  
end gateOR3;

ARCHITECTURE func of gateOR3 is 
BEGIN    
	out1 <= in1 OR in2 OR in3;
END func;