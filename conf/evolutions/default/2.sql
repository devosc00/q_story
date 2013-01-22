# --- Sample dataset

# --- !Ups

insert into company(id,c_name,orders) values (1, 'Thinking Machines', 0);
insert into company(id,c_name,orders) values (2, 'Brainfuck', 0);
insert into company(id,c_name,orders) values (3, 'Electroneuron', 0);
insert into company(id,c_name,orders) values (4, 'Mindstorm gap', 0);
insert into company(id,c_name,orders) values (5, 'Watchdog delay', 0);
insert into material(id,m_name,amount,t_amount,dates) values (1,'POM white fi 50 1000',0,0,null);
insert into material(id,m_name,amount,t_amount,dates) values (2,'POM black 80/40 1000',0,0,null);
insert into material(id,m_name,amount,t_amount,dates) values (3,'ertacetal 50/40 1000',0,0,null);
insert into material(id,m_name,amount,t_amount,dates) values (4,'6SA white fi 100 1000',0,0,null);
insert into material(id,m_name,amount,t_amount,dates) values (5,'PEAK fi 20 1000',0,0,null);
insert into project(id,name,end_date,order_,done_parts,mat_count,company_id,material_id) values (1,'project','11-11-2011',1000,100,2.5,1,1);

# --- !Downs

delete from company;
delete from material;
delete from project;
delete from history;


