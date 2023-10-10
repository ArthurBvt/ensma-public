with Ada.Text_IO,
    Ada.Integer_Text_IO;
use Ada.Text_IO,
    Ada.Integer_Text_IO;

procedure Fact_Rec is
    function Fact_Calc (Val : Integer) return Integer;

    function Fact_Calc (Val : Integer) return Integer is
    begin
        if Val > 1 then
            return Val * Fact_Calc (Val => Val - 1);
        else
            return 1;
        end if;
    end Fact_Calc;
    --  ======
    N, R : Integer;
begin
    Put (Item => "Donner un entier (>=0) : ");
    Get (Item => N);
    R := Fact_Calc (Val => N);
    Put (Item => N, Width => 1);
    Put (Item => "! = ");
    Put (Item => R, Width => 1);
    New_Line;
end Fact_Rec;