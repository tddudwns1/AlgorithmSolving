select distinct
    ID,
    EMAIL,
    FIRST_NAME,
    LAST_NAME
from
    DEVELOPERS d
    join
    SKILLCODES s
        on d.SKILL_CODE & s.CODE
        and s.CATEGORY = 'Front End'
order by
    ID