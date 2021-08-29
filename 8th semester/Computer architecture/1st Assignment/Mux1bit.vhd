/**************Team******************/
/*1.ANGELIKI ALESTA [3130251]********/
/*2.THOMAS KONSTANTINIDIS [3160074]**/
/************************************/

LIBRARY ieee;
Use ieee.std_logic_1164.all;


Entity Mux1bit is

port(in1, in2, sig: IN std_logic;
	  output: OUT std_logic);
end Mux1bit;

Architecture Logicfunc of Mux1bit is
Begin
   output <= (in1 AND NOT sig) OR (in2 AND sig);
end Logicfunc;