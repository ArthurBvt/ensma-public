with Ada.Text_IO, Ada.Integer_Text_IO, Ada.Command_Line;
use Ada.Text_IO, Ada.Integer_Text_IO, Ada.Command_Line;

procedure inter is

    type T_Tab is array (Positive range <>) of Integer;

    procedure Affiche_Tab (Tab : T_Tab);
    function Interclasse (Tab_1 : T_Tab; Tab_2 : T_Tab) return T_Tab;

    procedure Affiche_Tab (Tab : T_Tab) is
    begin
        for I in Tab'Range loop
            Put (Item);
        end loop;
    end Affiche_Tab;
begin

end inter;