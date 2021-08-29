/**************Team******************/
/*1.ANGELIKI ALESTA [3130251]********/
/*2.THOMAS KONSTANTINIDIS [3160074]**/
/************************************/

LIBRARY ieee;
Use ieee.std_logic_1164.all;

Entity gateXOR3 is

port(in1, in2, in3: IN std_logic;
	  out1: OUT std_logic);  
end gateXOR3;

ARCHITECTURE Logicfunc of gateXOR3 is 
BEGIN    
	out1 <= in1 XOR in2 XOR in3;
END Logicfunc;