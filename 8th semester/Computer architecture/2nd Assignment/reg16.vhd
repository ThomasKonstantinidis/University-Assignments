/**************Team******************/
/*1.ANGELIKI ALESTA [3130251]********/
/*2.THOMAS KONSTANTINIDIS [3160074]**/
/************************************/


library IEEE;
use IEEE.std_logic_1164.all;
use IEEE.numeric_std.all;

entity reg16 is 
	
	generic(
		n : INTEGER := 16
	);
	port( Input : IN std_logic_vector(n-1 downto 0);
			Enable, Clock: IN std_logic;
			Output : OUT STD_logic_vector(n-1 DOWnto 0)
	);
end reg16;

architecture Logicfunc of reg16 is
	component reg is
		Port (Input, Clock, Enable :IN STD_logic;
				Output :OUT STD_logic);
		end component;
		
begin
	G0:  reg port map(Input(0),  Clock, Enable, Output(0));
	G1:  reg port map(Input(1),  Clock, Enable, Output(1));
	G2:  reg port map(Input(2),  Clock, Enable, Output(2));
	G3:  reg port map(Input(3),  Clock, Enable, Output(3));
	G4:  reg port map(Input(4),  Clock, Enable, Output(4));
	G5:  reg port map(Input(5),  Clock, Enable, Output(5));
	G6:  reg port map(Input(6),  Clock, Enable, Output(6));
	G7:  reg port map(Input(7),  Clock, Enable, Output(7));
	G8:  reg port map(Input(8),  Clock, Enable, Output(8));
	G9:  reg port map(Input(9),  Clock, Enable, Output(9));
	G10: reg port map(Input(10), Clock, Enable, Output(10));
	G11: reg port map(Input(11), Clock, Enable, Output(11));
	G12: reg port map(Input(12), Clock, Enable, Output(12));
	G13: reg port map(Input(13), Clock, Enable, Output(13));
	G14: reg port map(Input(14), Clock, Enable, Output(14));
	G15: reg port map(Input(15), Clock, Enable, Output(15));
	
end  Logicfunc;
				
