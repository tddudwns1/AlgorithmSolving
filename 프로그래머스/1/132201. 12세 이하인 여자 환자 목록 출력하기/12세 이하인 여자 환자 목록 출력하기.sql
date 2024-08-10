SELECT
    p.pt_name as pt_name,
    p.pt_no as pt_no,
    p.gend_cd as gend_cd,
    p.age as age,
    case
        when p.tlno is null then "NONE"
        else p.tlno
    end tlno
from
    patient p
where
    p.age <= 12
    and p.gend_cd = "W"
order by
    age desc,
    p.pt_name