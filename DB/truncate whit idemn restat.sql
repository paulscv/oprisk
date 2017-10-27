truncate public.users RESTART IDENTITY CASCADE;
--SELECT pg_catalog.setval('sub_factors_id_seq', 1, false);

--select * from risk_event_types ret inner join risk_event_sub_types rest on ret.id = rest.id_type inner join risk_event_type_examples rete on rest.id = rete.id_event_sub_type
--order by rest.id;