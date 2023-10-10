pragma Warnings (Off);
pragma Ada_95;
pragma Source_File_Name (ada_main, Spec_File_Name => "b__test_deque.ads");
pragma Source_File_Name (ada_main, Body_File_Name => "b__test_deque.adb");
pragma Suppress (Overflow_Check);
with Ada.Exceptions;

package body ada_main is

   E076 : Short_Integer; pragma Import (Ada, E076, "system__os_lib_E");
   E009 : Short_Integer; pragma Import (Ada, E009, "ada__exceptions_E");
   E014 : Short_Integer; pragma Import (Ada, E014, "system__soft_links_E");
   E026 : Short_Integer; pragma Import (Ada, E026, "system__exception_table_E");
   E041 : Short_Integer; pragma Import (Ada, E041, "ada__containers_E");
   E071 : Short_Integer; pragma Import (Ada, E071, "ada__io_exceptions_E");
   E056 : Short_Integer; pragma Import (Ada, E056, "ada__strings_E");
   E058 : Short_Integer; pragma Import (Ada, E058, "ada__strings__maps_E");
   E062 : Short_Integer; pragma Import (Ada, E062, "ada__strings__maps__constants_E");
   E046 : Short_Integer; pragma Import (Ada, E046, "interfaces__c_E");
   E028 : Short_Integer; pragma Import (Ada, E028, "system__exceptions_E");
   E082 : Short_Integer; pragma Import (Ada, E082, "system__object_reader_E");
   E051 : Short_Integer; pragma Import (Ada, E051, "system__dwarf_lines_E");
   E022 : Short_Integer; pragma Import (Ada, E022, "system__soft_links__initialize_E");
   E040 : Short_Integer; pragma Import (Ada, E040, "system__traceback__symbolic_E");
   E102 : Short_Integer; pragma Import (Ada, E102, "ada__tags_E");
   E100 : Short_Integer; pragma Import (Ada, E100, "ada__streams_E");
   E114 : Short_Integer; pragma Import (Ada, E114, "system__file_control_block_E");
   E113 : Short_Integer; pragma Import (Ada, E113, "system__finalization_root_E");
   E111 : Short_Integer; pragma Import (Ada, E111, "ada__finalization_E");
   E110 : Short_Integer; pragma Import (Ada, E110, "system__file_io_E");
   E007 : Short_Integer; pragma Import (Ada, E007, "ada__text_io_E");
   E002 : Short_Integer; pragma Import (Ada, E002, "libdeque_E");

   Sec_Default_Sized_Stacks : array (1 .. 1) of aliased System.Secondary_Stack.SS_Stack (System.Parameters.Runtime_Default_Sec_Stack_Size);

   Local_Priority_Specific_Dispatching : constant String := "";
   Local_Interrupt_States : constant String := "";

   Is_Elaborated : Boolean := False;

   procedure finalize_library is
   begin
      E007 := E007 - 1;
      declare
         procedure F1;
         pragma Import (Ada, F1, "ada__text_io__finalize_spec");
      begin
         if E007 = 0 then
            F1;
         end if;
      end;
      declare
         procedure F2;
         pragma Import (Ada, F2, "system__file_io__finalize_body");
      begin
         E110 := E110 - 1;
         if E110 = 0 then
            F2;
         end if;
      end;
      declare
         procedure Reraise_Library_Exception_If_Any;
            pragma Import (Ada, Reraise_Library_Exception_If_Any, "__gnat_reraise_library_exception_if_any");
      begin
         Reraise_Library_Exception_If_Any;
      end;
   end finalize_library;

   procedure adafinal is
      procedure s_stalib_adafinal;
      pragma Import (C, s_stalib_adafinal, "system__standard_library__adafinal");

      procedure Runtime_Finalize;
      pragma Import (C, Runtime_Finalize, "__gnat_runtime_finalize");

   begin
      if not Is_Elaborated then
         return;
      end if;
      Is_Elaborated := False;
      Runtime_Finalize;
      s_stalib_adafinal;
   end adafinal;

   type No_Param_Proc is access procedure;
   pragma Favor_Top_Level (No_Param_Proc);

   procedure adainit is
      Main_Priority : Integer;
      pragma Import (C, Main_Priority, "__gl_main_priority");
      Time_Slice_Value : Integer;
      pragma Import (C, Time_Slice_Value, "__gl_time_slice_val");
      WC_Encoding : Character;
      pragma Import (C, WC_Encoding, "__gl_wc_encoding");
      Locking_Policy : Character;
      pragma Import (C, Locking_Policy, "__gl_locking_policy");
      Queuing_Policy : Character;
      pragma Import (C, Queuing_Policy, "__gl_queuing_policy");
      Task_Dispatching_Policy : Character;
      pragma Import (C, Task_Dispatching_Policy, "__gl_task_dispatching_policy");
      Priority_Specific_Dispatching : System.Address;
      pragma Import (C, Priority_Specific_Dispatching, "__gl_priority_specific_dispatching");
      Num_Specific_Dispatching : Integer;
      pragma Import (C, Num_Specific_Dispatching, "__gl_num_specific_dispatching");
      Main_CPU : Integer;
      pragma Import (C, Main_CPU, "__gl_main_cpu");
      Interrupt_States : System.Address;
      pragma Import (C, Interrupt_States, "__gl_interrupt_states");
      Num_Interrupt_States : Integer;
      pragma Import (C, Num_Interrupt_States, "__gl_num_interrupt_states");
      Unreserve_All_Interrupts : Integer;
      pragma Import (C, Unreserve_All_Interrupts, "__gl_unreserve_all_interrupts");
      Detect_Blocking : Integer;
      pragma Import (C, Detect_Blocking, "__gl_detect_blocking");
      Default_Stack_Size : Integer;
      pragma Import (C, Default_Stack_Size, "__gl_default_stack_size");
      Default_Secondary_Stack_Size : System.Parameters.Size_Type;
      pragma Import (C, Default_Secondary_Stack_Size, "__gnat_default_ss_size");
      Leap_Seconds_Support : Integer;
      pragma Import (C, Leap_Seconds_Support, "__gl_leap_seconds_support");
      Bind_Env_Addr : System.Address;
      pragma Import (C, Bind_Env_Addr, "__gl_bind_env_addr");

      procedure Runtime_Initialize (Install_Handler : Integer);
      pragma Import (C, Runtime_Initialize, "__gnat_runtime_initialize");

      Finalize_Library_Objects : No_Param_Proc;
      pragma Import (C, Finalize_Library_Objects, "__gnat_finalize_library_objects");
      Binder_Sec_Stacks_Count : Natural;
      pragma Import (Ada, Binder_Sec_Stacks_Count, "__gnat_binder_ss_count");
      Default_Sized_SS_Pool : System.Address;
      pragma Import (Ada, Default_Sized_SS_Pool, "__gnat_default_ss_pool");

   begin
      if Is_Elaborated then
         return;
      end if;
      Is_Elaborated := True;
      Main_Priority := -1;
      Time_Slice_Value := -1;
      WC_Encoding := 'b';
      Locking_Policy := ' ';
      Queuing_Policy := ' ';
      Task_Dispatching_Policy := ' ';
      Priority_Specific_Dispatching :=
        Local_Priority_Specific_Dispatching'Address;
      Num_Specific_Dispatching := 0;
      Main_CPU := -1;
      Interrupt_States := Local_Interrupt_States'Address;
      Num_Interrupt_States := 0;
      Unreserve_All_Interrupts := 0;
      Detect_Blocking := 0;
      Default_Stack_Size := -1;
      Leap_Seconds_Support := 0;

      ada_main'Elab_Body;
      Default_Secondary_Stack_Size := System.Parameters.Runtime_Default_Sec_Stack_Size;
      Binder_Sec_Stacks_Count := 1;
      Default_Sized_SS_Pool := Sec_Default_Sized_Stacks'Address;

      Runtime_Initialize (1);

      Finalize_Library_Objects := finalize_library'access;

      if E009 = 0 then
         Ada.Exceptions'Elab_Spec;
      end if;
      if E014 = 0 then
         System.Soft_Links'Elab_Spec;
      end if;
      if E026 = 0 then
         System.Exception_Table'Elab_Body;
      end if;
      E026 := E026 + 1;
      if E041 = 0 then
         Ada.Containers'Elab_Spec;
      end if;
      E041 := E041 + 1;
      if E071 = 0 then
         Ada.Io_Exceptions'Elab_Spec;
      end if;
      E071 := E071 + 1;
      if E056 = 0 then
         Ada.Strings'Elab_Spec;
      end if;
      E056 := E056 + 1;
      if E058 = 0 then
         Ada.Strings.Maps'Elab_Spec;
      end if;
      E058 := E058 + 1;
      if E062 = 0 then
         Ada.Strings.Maps.Constants'Elab_Spec;
      end if;
      E062 := E062 + 1;
      if E046 = 0 then
         Interfaces.C'Elab_Spec;
      end if;
      E046 := E046 + 1;
      if E028 = 0 then
         System.Exceptions'Elab_Spec;
      end if;
      E028 := E028 + 1;
      if E082 = 0 then
         System.Object_Reader'Elab_Spec;
      end if;
      E082 := E082 + 1;
      if E051 = 0 then
         System.Dwarf_Lines'Elab_Spec;
      end if;
      E051 := E051 + 1;
      if E076 = 0 then
         System.Os_Lib'Elab_Body;
      end if;
      E076 := E076 + 1;
      if E022 = 0 then
         System.Soft_Links.Initialize'Elab_Body;
      end if;
      E022 := E022 + 1;
      E014 := E014 + 1;
      if E040 = 0 then
         System.Traceback.Symbolic'Elab_Body;
      end if;
      E040 := E040 + 1;
      E009 := E009 + 1;
      if E102 = 0 then
         Ada.Tags'Elab_Spec;
      end if;
      if E102 = 0 then
         Ada.Tags'Elab_Body;
      end if;
      E102 := E102 + 1;
      if E100 = 0 then
         Ada.Streams'Elab_Spec;
      end if;
      E100 := E100 + 1;
      if E114 = 0 then
         System.File_Control_Block'Elab_Spec;
      end if;
      E114 := E114 + 1;
      if E113 = 0 then
         System.Finalization_Root'Elab_Spec;
      end if;
      E113 := E113 + 1;
      if E111 = 0 then
         Ada.Finalization'Elab_Spec;
      end if;
      E111 := E111 + 1;
      if E110 = 0 then
         System.File_Io'Elab_Body;
      end if;
      E110 := E110 + 1;
      if E007 = 0 then
         Ada.Text_Io'Elab_Spec;
      end if;
      if E007 = 0 then
         Ada.Text_Io'Elab_Body;
      end if;
      E007 := E007 + 1;
      E002 := E002 + 1;
   end adainit;

--  BEGIN Object file/option list
   --   -L/home/bouveta/Windows/GIT/ensma/TAB/TD03_TDA/src_ada/lib/
   --   -L/home/bouveta/Windows/GIT/ensma/TAB/TD03_TDA/.obj/c++/debug/
   --   -L/home/bouveta/Windows/GIT/ensma/TAB/TD03_TDA/src_c/lib/
   --   -L/home/bouveta/Windows/GIT/ensma/TAB/TD03_TDA/src_ada/lib/
   --   -L/usr/lib/gcc/x86_64-linux-gnu/10/adalib/
   --   -shared
   --   -lgnat-10
   --   -ldl
--  END Object file/option list   

end ada_main;
