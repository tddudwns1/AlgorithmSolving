select
    rd.ID
from
    ECOLI_DATA st
    left join
    ECOLI_DATA nd
        on st.ID = nd.PARENT_ID
    left join
    ECOLI_DATA rd
        on nd.ID = rd.PARENT_ID
where
    st.PARENT_ID is null
    and rd.ID is not null
order by
    ID