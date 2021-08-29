/**************Team******************/
/*1.ANGELIKI ALESTA [3130251]********/
/*2.THOMAS KONSTANTINIDIS [3160074]**/
/************************************/


library IEEE;
use IEEE.std_logic_1164.all;


entity regFile is 
	
	generic(
		n : INTEGER := 16;
		k : INTEGER := 3;
		regNum : INTEGER := 8
	);
	port( Clock : in std_logic;
			Write1 : in std_logic_vector(n-1 downto 0);
			Write1AD,Read1AD,Read2AD :in std_logic_vector(k-1 downto 0);
			Read1,Read2 : out std_logic_vector(n-1 downto 0);
			OUTall : out std_logic_vector(n*regNum-1 downto 0)
	);
end regFile;

architecture LogicFunc of regFile is
	
	component reg0 is
		PORT(Input: IN std_logic_vector(n-1 downto 0);
			  Enable,Clock : IN std_logic;
			  Output : OUT std_logic_vector(n-1 downto 0) );
	end component;
	component reg16 is
		PORT(Input: IN std_logic_vector(n-1 downto 0);
			  Enable,Clock : IN std_logic;
			  Output : OUT std_logic_vector(n-1 downto 0) );
	end component;
	component decode3to8 is
		PORT(Input: IN std_logic_vector(k-1 downto 0);
			  Output : OUT std_logic_vector(regNum-1 downto 0) );
	end component;
	component mux8to1 is
		PORT(Input0,Input1,Input2,Input3,Input4,Input5,Input6,Input7: IN std_logic_vector(n-1 downto 0);
			  Choice : IN std_logic_vector(k-1 downto 0);
			  Output : OUT std_logic_vector(n-1 downto 0) );
	end component;

   signal enableSigs: std_logic_vector(regNum-1 downto 0);
	signal re0,re1,re2,re3,re4,re5,re6,re7 : std_logic_vector(n-1 downto 0);
	
begin
	
	G0: decode3to8 port map(Write1AD,enableSigs);
	
	G1: reg0 		port map(Write1, enableSigs(0), Clock, re0);
	G2: reg16  		port map(Write1, enableSigs(1), Clock, re1);
	G3: reg16  		port map(Write1, enableSigs(2), Clock, re2);
	G4: reg16  		port map(Write1, enableSigs(3), Clock, re3);
	G5: reg16  		port map(Write1, enableSigs(4), Clock, re4);
	G6: reg16  		port map(Write1, enableSigs(5), Clock, re5);
	G7: reg16  		port map(Write1, enableSigs(6), Clock, re6);
	G8: reg16 		port map(Write1, enableSigs(7), Clock, re7);
	
	G9:  mux8to1 	port map(re0,re1,re2,re3,re4,re5,re6,re7,Read1AD,Read1);
	G10: mux8to1 	port map(re0,re1,re2,re3,re4,re5,re6,re7,Read2AD,Read2);
	
	OUTall <= re7 & re6 & re5 & re4 & re3 & re2 & re1 & re0;
	
end LogicFunc;
