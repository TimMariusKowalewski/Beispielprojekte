:- use_module(library(clpfd)).

% es gibt Lehrer
ist_lehrer(schmidt).
ist_lehrer(mueller).
ist_lehrer(luedenscheid).
ist_lehrer(heinrichs).

% es gibt Unterrichtsfächer
ist_fach(mathe).
ist_fach(englisch).
ist_fach(deutsch).
ist_fach(bio).
ist_fach(physik).
ist_fach(informatik).
ist_fach(geschichte).
ist_fach(sport).

% Lehrer unterrichten Unterrichtsfächer
lehrerunterrichtet_fach(schmidt, mathe).
lehrerunterrichtet_fach(schmidt, englisch).
lehrerunterrichtet_fach(mueller, mathe).
lehrerunterrichtet_fach(mueller, bio).
lehrerunterrichtet_fach(luedenscheid, geschichte).
lehrerunterrichtet_fach(luedenscheid, sport).
lehrerunterrichtet_fach(heinrichs, deutsch).
lehrerunterrichtet_fach(heinrichs, informatik).

% es gibt Klassen
ist_klasse("5a").
ist_klasse("5b").
ist_klasse("5c").
ist_klasse("5d").
ist_klasse("5e").

% es gibt Klassenlehrer
ist_klassenlehrer(schmidt, "5a").

lehrerunterrichtet_klasse_infach_zuuhrzeit(heinrichs, "5a", informatik, "Stunde1").

% ein Fach kann nur unterrichtet werden, wenn ein Lehrer verfügbar ist
fach_kann_unterrichtet_werden(Fach) :-
	ist_fach(Fach),
	ist_lehrer(Lehrer),
	lehrerunterrichtet_fach(Lehrer, Fach).

% einzigartige Liste
unique_list([]).
unique_list([X|Xs]) :-
	\+ memberchk(X, Xs),
	unique_list(Xs).

% ein Schultag besteht aus 6 Stunden
schultag(L) :-
	L = [_,_,_,_,_,_],
	maplist(fach_kann_unterrichtet_werden,L),
	unique_list(L).

% der Stundenplan besteht aus 5 Schultagen
stundenplan(L) :-
	%L = [[sport,_,_,_,_,_],_,_,_,_],
	L = [_,_,_,_,_],
	maplist(schultag,L).

stundenplan_ausgabe(L) :-
	stundenplan(L).
