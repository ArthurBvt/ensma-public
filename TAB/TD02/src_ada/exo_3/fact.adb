with Ada.Text_IO,
    Ada.Integer_Text_IO,
    Ada.Command_Line;
use Ada.Text_IO,
    Ada.Integer_Text_IO,
    Ada.Command_Line;

procedure Fact is
    procedure Fact_Calc (Val : Integer; Res : out Integer);

    procedure Fact_Calc (Val : Integer; Res : out Integer) is
    begin
        Res := 1;
        for I in 1 .. Val loop
            Res := Res * I;
        end loop;
    end Fact_Calc;
    --  ======
    N, R : Integer;
begin
    if Argument_Count < 1 or Argument_Count >= 2 then
        Put_Line (Item => "Nb paramètre(s) invalide...");
    else
        N := Integer'Value (Argument (1));
        Fact_Calc (Val => N, Res => R);
        Put (Item => N, Width => 1);
        Put (Item => "! = ");
        Put (Item => R, Width => 1);
        New_Line;
    end if;
end Fact;