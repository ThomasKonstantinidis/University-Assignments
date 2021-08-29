/**************Team******************/
/*1.ANGELIKI ALESTA [3130251]********/
/*2.THOMAS KONSTANTINIDIS [3160074]**/
/************************************/


library IEEE;
use IEEE.std_logic_1164.all;

entity AluControl is 

	port( opCode : in std_logic_vector(3 downto 0);
			func : in std_logic_vector(2 downto 0);
		   output : out std_logic_vector (3 downto 0) 
	);
end AluControl;

architecture behavioral of AluControl is
	
begin 
	process(opCode, func) begin 
		case opCode is
			when "0000" =>
						output(3) <= '0';
						output(2 downto 0) <= func(2 downto 0);
			when others => output <= opCode;
		end case;
	end process;

end behavioral;
