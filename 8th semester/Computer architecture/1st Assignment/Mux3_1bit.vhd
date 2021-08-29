/**************Team******************/
/*1.ANGELIKI ALESTA [3130251]********/
/*2.THOMAS KONSTANTINIDIS [3160074]**/
/************************************/

LIBRARY ieee;
Use ieee.std_logic_1164.all;

Entity Mux3_1bit is

port(in1, in2, in3, sig1, sig2: IN std_logic;
	  output: OUT std_logic);
end Mux3_1bit;

Architecture Logicfunc of Mux3_1bit is

component Mux1bit
   Port(in1, in2, sig: IN std_logic;
	     output:OUT std_logic);
end component;

signal f :std_logic;

Begin

  G0: Mux1bit port map (in1, in2, sig1, f);
  G1: Mux1bit port map (f, in3, sig2, output); --PROSEXE EDW TO OUTPUT
  
end Logicfunc;