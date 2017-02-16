insert into configuration (keyy, value) values ('OK', 'OK VALUE');

insert into tags (name) values ( 'Algebra' );
insert into tags (name) values ( 'Bryły' );
insert into tags (name) values ( 'Analiza' );
insert into tags (name) values ( 'Geometria' );
insert into tags (name) values ( 'Analityka' );
insert into tags (name) values ( 'Trygonometria' );
insert into tags (name) values ( 'Prawdopodobieństwo' );
insert into tags (name) values ( 'Kombinatoryka' );
insert into tags (name) values ( 'Topologia' );
insert into tags (name) values ( 'Stereometria' );
insert into tags (name) values ( 'Wielomiany' );
insert into tags (name) values ( 'Logarytmy' );
insert into tags (name) values ( 'Całki' );
insert into tags (name) values ( 'Pochodne' );
insert into tags (name) values ( 'Tales' );
insert into tags (name) values ( 'Pitagoras' );
insert into tags (name) values ( 'Równania' );
insert into tags (name) values ( 'Funkcje' );

insert into menu (id, name, parent_id) values (1, 'Podstawowe własności figur geometrycznych na płaszczyźnie', null);

insert into menu (id, name, parent_id) values (2, 'Okrąg i koło', 1);
insert into menu (id, name, parent_id) values (11, 'Wzajemne położenie prostej i okręgu', 2);
insert into menu (id, name, parent_id) values (12, 'Wzajemne położenie dwóch okręgów', 2);
insert into menu (id, name, parent_id) values (13, 'Kąty i koła', 2);

insert into menu (id, name, parent_id) values (3, 'Czworokąty', 1);
insert into menu (id, name, parent_id) values (14, 'Trapezy', 3);
insert into menu (id, name, parent_id) values (15, 'Równoległoboki', 3);
insert into menu (id, name, parent_id) values (16, 'Trapezoidy', 3);

insert into menu (id, name, parent_id) values (4, 'Wielokąty wpisane w okrąg i opisane na okręgu', 1);
insert into menu (id, name, parent_id) values (17, 'Trójkąt wpisany w okrąg i opisany na okręgu', 4);
insert into menu (id, name, parent_id) values (18, 'Okrąg wpisany w czworokąt', 4);
insert into menu (id, name, parent_id) values (19, 'Okrąg opisany na czworokącie', 4);

insert into menu (id, name, parent_id) values (5, 'Funkcja kwadratowa', 1);
insert into menu (id, name, parent_id) values (6, 'Jednomian stopnia drugiego', 5);
insert into menu (id, name, parent_id) values (7, 'Postać ogólna i kanoniczna funkcji kwadratowej', 5);
insert into menu (id, name, parent_id) values (8, 'Miejsce zerowe i postać iloczynowa trójmianu kwadratowego', 5);
insert into menu (id, name, parent_id) values (9, 'Równania kwadratowe', 5);
insert into menu (id, name, parent_id) values (20, 'Rownania prowadzące do równań kwadratowych', 9);

insert into menu (id, name, parent_id) values (10, 'Funkcja liniowa', 1);