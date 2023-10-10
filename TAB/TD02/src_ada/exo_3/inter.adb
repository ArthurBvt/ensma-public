with Ada.Text_IO,
    Ada.Integer_Text_IO,
    Ada.Command_Line;
use Ada.Text_IO,
    Ada.Integer_Text_IO,
    Ada.Command_Line;

procedure inter is
    type T_Tab is array (Positive range <>) of Integer;

    procedure Affiche_Tab (Tab : T_Tab);
    function Interclasse (Tab_1 : T_Tab; Tab_2 : T_Tab) return T_Tab;

    procedure Affiche_Tab (Tab : T_Tab) is
    begin
        for I in Tab'Range loop
            Put (Item => Tab (I), Width => 6);
        end loop;
        New_Line (Spacing => 1);
    end Affiche_Tab;

    function Interclasse (Tab_1 : T_Tab; Tab_2 : T_Tab) return T_Tab is
        I, J : Positive := 1;
        Tab_3 : T_Tab (1 .. Tab_1'Length + Tab_2'Length);
    begin
        while I <= Tab_1'Length and J <= Tab_2'Length loop
            if Tab_1 (I) < Tab_2 (J) then
                Tab_3 (I + J - 1) := Tab_1 (I);
                I := I + 1;
            else
                Tab_3 (I + J - 1) := Tab_2 (J);
                J := J + 1;
            end if;
        end loop;
        if I = Tab_1'Length + 1 then
            for K in J .. Tab_2'Length loop
                Tab_3 (I + K - 1) := Tab_2 (K);
            end loop;
        else
            for K in I .. Tab_1'Length loop
                Tab_3 (K + J - 1) := Tab_1 (K);
            end loop;
        end if;
        return Tab_3;
    end Interclasse;
    --  ====
    M, N : Positive;
begin
    if Argument_Count <= 4 then
        Put_Line (Item => "Paramètre(s) manquant(s)...");
    else
        M := Positive'Value (Argument (Number => 1));
        N := Positive'Value (Argument (Number => 1 + M + 1));
        data : declare
            T1 : T_Tab (1 .. M);
            T2 : T_Tab (1 .. N);
            T3 : T_Tab (1 .. M + N);
        begin
            for I in 1 .. M loop
                T1 (I) := Integer'Value (Argument (Number => 1 + I));
            end loop;
            for I in 1 .. N loop
                T2 (I) := Integer'Value (Argument (Number => 1 + M + 1 + I));
            end loop;
            Put (Item => "T1 : "); Affiche_Tab (Tab => T1);
            Put (Item => "T1 : "); Affiche_Tab (Tab => T2);
            T3 := Interclasse (Tab_1 => T1, Tab_2 => T2);
            Put (Item => "T3 : "); Affiche_Tab (Tab => T3);
        end data;
    end if;
end inter;