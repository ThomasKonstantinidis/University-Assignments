/**************Team******************/
/*1.THOMAS KONSTANTINIDIS [3160074]**/
/*2.ANGELIKI ALESTA [3130251]********/
/************************************/


library IEEE;
use IEEE.std_logic_1164.all;


ENTITY JRSelector IS
	generic(
		n: INTEGER := 16		
	);
	port(
		jumpAD, branchAd,PCP2AD : in std_logic_vector(n-1 downto 0);
		JRopcode : in std_logic_vector(1 downto 0);
		result : out std_logic_vector(n-1 downto 0)
	);
end JRSelector;

architecture behavior of JRSelector is
begin
process(JRopcode)
begin
	case JRopcode is
		when "00" => result <= PCP2AD;
		when "01" => result <= jumpAD;
		when "10" => result <= branchAd;
		when others => result <= PCP2AD;
	end case;
end process;

end behavior;
