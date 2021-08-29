/**************Team******************/
/*1.ANGELIKI ALESTA [3130251]********/
/*2.THOMAS KONSTANTINIDIS [3160074]**/
/************************************/


LIBRARY ieee;
Use ieee.std_logic_1164.all;
Use ieee.std_logic_signed.all;


Entity ALU is
 GENERIC( n:integer := 16 );
	 Port(Input1,Input2:IN std_logic_vector(n-1 DOWNTO 0);
	      Carryin: IN std_logic;
			CarryOut:OUT std_logic;
			Operation:IN std_logic_vector(3 DOWNTO 0);
			Output: OUT std_logic_vector(n-1 DOWNTO 0));
end ALU;


ARCHITECTURE logicfunc of ALU is 
   
	component ALUSlice is 
	      Port (Input1,Input2,Carryin,AInvert,BInvert,Op1,Op2 : IN std_logic;
			      Output,Carryout: OUT std_logic);
	end component;
	
	signal CarryOuts, tempOutput: std_logic_vector(n-1 DOWNTO 0);
	signal COUT: std_logic;
	
	constant ADD_FUNC:std_logic_vector(3 DOWNTO 0) := "0010";
	constant SUB_FUNC:std_logic_vector(3 DOWNTO 0) := "0011";
	constant AND_FUNC:std_logic_vector(3 DOWNTO 0) := "0000";
	constant OR_FUNC:	std_logic_vector(3 DOWNTO 0) := "0001";
	constant GEQ_FUNC:std_logic_vector(3 DOWNTO 0) := "0101";
	constant NOT_FUNC:std_logic_vector(3 DOWNTO 0) := "0110";
	
Begin
	G0:  ALUSlice port map (Input1(0), Input2(0), Carryin,		Operation(3), Operation(2), Operation(0), Operation(1), tempOutput(0),  CarryOuts(0));
	G1:  ALUSlice port map (Input1(1), Input2(1), CarryOuts(0), Operation(3), Operation(2), Operation(0), Operation(1), tempOutput(1),  CarryOuts(1));
	G2:  ALUSlice port map (Input1(2), Input2(2), CarryOuts(1), Operation(3), Operation(2), Operation(0), Operation(1), tempOutput(2),  CarryOuts(2));
	G3:  ALUSlice port map (Input1(3), Input2(3), CarryOuts(2), Operation(3), Operation(2), Operation(0), Operation(1), tempOutput(3),  CarryOuts(3));
	G4:  ALUSlice port map (Input1(4), Input2(4), CarryOuts(3), Operation(3), Operation(2), Operation(0), Operation(1), tempOutput(4),  CarryOuts(4));
	G5:  ALUSlice port map (Input1(5), Input2(5), CarryOuts(4), Operation(3), Operation(2), Operation(0), Operation(1), tempOutput(5),  CarryOuts(5));
	G6:  ALUSlice port map (Input1(6), Input2(6), CarryOuts(5), Operation(3), Operation(2), Operation(0), Operation(1), tempOutput(6),  CarryOuts(6));
	G7:  ALUSlice port map (Input1(7), Input2(7), CarryOuts(6), Operation(3), Operation(2), Operation(0), Operation(1), tempOutput(7),  CarryOuts(7));
	G8:  ALUSlice port map (Input1(8), Input2(8), CarryOuts(7), Operation(3), Operation(2), Operation(0), Operation(1), tempOutput(8),  CarryOuts(8));
	G9:  ALUSlice port map (Input1(9), Input2(9), CarryOuts(8), Operation(3), Operation(2), Operation(0), Operation(1), tempOutput(9),  CarryOuts(9));
	G10: ALUSlice port map (Input1(10), Input2(10), CarryOuts(9), Operation(3), Operation(2), Operation(0), Operation(1), tempOutput(10), CarryOuts(10));
	G11: ALUSlice port map (Input1(11), Input2(11), CarryOuts(10), Operation(3), Operation(2), Operation(0), Operation(1), tempOutput(11), CarryOuts(11));
	G12: ALUSlice port map (Input1(12), Input2(12), CarryOuts(11), Operation(3), Operation(2), Operation(0), Operation(1), tempOutput(12), CarryOuts(12));
	G13: ALUSlice port map (Input1(13), Input2(13), CarryOuts(12), Operation(3), Operation(2), Operation(0), Operation(1), tempOutput(13), CarryOuts(13));
	G14: ALUSlice port map (Input1(14), Input2(14), CarryOuts(13), Operation(3), Operation(2), Operation(0), Operation(1), tempOutput(14), CarryOuts(14));
	G15: ALUSlice port map (Input1(15), Input2(15), CarryOuts(14), Operation(3), Operation(2), Operation(0), Operation(1), tempOutput(15), COUT);
	
	
	process(Operation)
	   variable temp: std_logic_vector(n-1 DOWNTO 0);
		--variable temp2:std_logic_vector(2*n-1 DOWNTO 0);
		variable temp3,temp4 : std_logic;
	begin
	
	   case Operation is
		   when ADD_FUNC =>
			   temp := tempOutput;
			when SUB_FUNC =>
			   temp := Input1 - Input2;
			when AND_FUNC =>
			   temp := tempOutput;
			when OR_FUNC =>
			   temp := tempOutput;
			when GEQ_FUNC =>
			   temp := (OTHERS => NOT Input1(n-1));
				temp4 := '0';
				temp3 := NOT(Input1(n-1));
			when NOT_FUNC =>
			   temp := (OTHERS => '0');
				if (Input1 = (n-1 DOWNTO 0 => '0')) then 
				    temp(0) := '1';
			   end if;
			when others =>
			   temp := Input1 - Input2;
		end case;
		
		IF(temp4 = '0')THEN 
		  Carryout <= temp3;
		ELSE
		  Carryout <= COUT;
		END IF;
		
		Output <= temp;
	end process;
	
END logicfunc;
		