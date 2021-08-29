/**************Team******************/
/*1.ANGELIKI ALESTA [3130251]********/
/*2.THOMAS KONSTANTINIDIS [3160074]**/
/************************************/

LIBRARY ieee;
Use ieee.std_logic_1164.all;
Use ieee.std_logic_signed.all;


Entity ALUSlice is
	 Port(
			Input1,Input2, Carryin, AInvert,BInvert,Op1,Op2:IN std_logic;
	    	Output, Carryout:OUT std_logic);
end ALUSlice;


Architecture func of ALUSlice is
   
	component fulladder is
	   Port(
			  input1, input2, carryin:IN std_logic;
		     sum, carryout :OUT std_logic);
	end component;
	
   component Mux1bit is
	   Port(
			  in1,in2,sig :IN std_logic;
		     output :OUT std_logic);
	end component;
	
    component Mux3_1bit is
	   Port(
			  in1,in2,in3,sig1,sig2:IN std_logic;
		     output:OUT std_logic);
	end component;
	
  signal f1,f2,f3,f4,f5: std_logic;
 
 Begin
	 G0:Mux1bit port map (Input1, NOT Input1, AInvert, f1);
	 G1:Mux1bit port map (Input2, NOT Input2, BInvert, f2);
	 f3 <= f1 AND f2;
	 f4 <= f1 OR f2;
	 G2: fulladder port map (f1,f2, Carryin, f5, Carryout);
	 G3: Mux3_1bit port map (f3, f4, f5, Op1, Op2, Output);
End func;
	
	
	
	