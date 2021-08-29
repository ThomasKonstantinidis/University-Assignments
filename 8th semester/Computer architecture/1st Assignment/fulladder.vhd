/**************Team******************/
/*1.ANGELIKI ALESTA [3130251]********/
/*2.THOMAS KONSTANTINIDIS [3160074]**/
/************************************/

LIBRARY ieee;
Use ieee.std_logic_1164.all;


Entity fulladder is

port(
	  input1, input2, carryin:  IN std_logic;
	  sum	, carryout: OUT std_logic);
	  
end fulladder;

ARCHITECTURE logicstruct of fulladder is 
     
 component gateAND2 
	 port(in1,in2: IN std_logic;
	      out1:OUT std_logic);
 end component;
 
 component gateOR3
    port (in1,in2,in3:IN std_logic;
	        out1: out std_logic);
 end component;

  component gateXOR3
    port (in1,in2,in3:IN std_logic;
	        out1: out std_logic);
 end component;
 
 SIGNAL s1,s2,s3: std_logic;
 
 begin
   V0: gateXOR3 port map(input1,input2,carryin,sum);
	V1: gateAND2 port map(input1, input2, s1);
	V2: gateAND2 port map(input1, carryin, s2);
	V3: gateAND2 port map(input2, carryin, s3);
	V4: gateOR3 port map(s1,s2,s3,carryout);
	
end logicstruct;