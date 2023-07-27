CREATE OR REPLACE FUNCTION ws.fn_get_user_nm(p_user_id bigint)
 RETURNS character varying
 LANGUAGE plpgsql
AS $function$
    declare v_user_nm varchar := '';
BEGIN
select user_nm into v_user_nm
from ws.tb_user a
where a.id = p_user_id;

return v_user_nm;
END;
$function$
;


CREATE OR REPLACE FUNCTION ws.fn_get_code_nm(p_code_grp_val character varying, p_code_val character varying)
 RETURNS character varying
 LANGUAGE plpgsql
AS $function$
    declare v_code_val varchar := '';
BEGIN

select b.code_nm into v_code_val
from ws.tb_code_grp a, ws.tb_code b
where a.id = b.code_grp_id
  and a.code_grp_val = p_code_grp_val
  and b.code_val  = p_code_val;

return v_code_val;

END;
$function$
;

