:- use_module(library(clpfd)).

list_count([],0).
list_count([_|T],N) :-
	!,
	list_count(T,M),
	writeln(N),
	N is M+1.
distinct_list_count([], _, 0).
distinct_list_count([X | T], X, N) :-
	!,
	distinct_list_count(T, X, N1),
	N is N1 + 1.
distinct_list_count([_ | T], X, N) :-
	distinct_list_count(T, X, N).


% Einsteinrätsel
select([],_). 
select([A|As],S) :- 	select(A,S,S1),
			select(As,S1).

left_of(A,B,C) :-
			append(_,[A,B|_],C).  

my_f(X,Y) :-
		Y #= X^2.

my_f2([],[]).
my_f2([X|Xt],[Y|Yt]) :-
	my_f(X,Y),
	my_f2(Xt,Yt).

next_to(A,B,C) :-
	left_of(A,B,C);
	left_of(B,A,C).

rechternachbar_von(X, Y) :-
	X in 1..5,
	Y in 1..5,
	X #= Y+1.

linkernachbar_von(X, Y) :-
	rechternachbar_von(Y,X).

nachbar_von(X, Y) :-
	linkernachbar_von(X, Y);
	rechternachbar_von(X, Y).


rechts_von(X,Y) :-
	W #= X - 1,
	W #> 0,
	(
		rechternachbar_von(X,Y);
		rechts_von(W,Y)
	).
	

links_von(X,Y) :-
	rechts_von(Y,X).

% (* house: color,nation,pet,drink,smokes *)
zebra(Owns, HS) :-
	HS =	[
			h(_,norwegian,_,_,_),
			h(blue,_,_,_,_),
			h(_,_,_,milk,_),
			_,
			_
		], 
	select(
		[
			h(red,brit,_,_,_),
			h(_,swede,dog,_,_),
			h(_,dane,_,tea,_),
			h(_,german,_,_,prince)
		],
		HS
	),
	select(
		[
			h(_,_,birds,_,pallmall),
			h(yellow,_,_,_,dunhill),
			h(_,_,_,beer,bluemaster)
		],
		HS
	), 
	%left_of( h(green,_,_,coffee,_), h(white,_,_,_,_), HS),
	linkernachbar_von( h(green,_,_,coffee,_), h(white,_,_,_,_), HS),
	%next_to( h(_,_,_,_,dunhill), h(_,_,horse,_,_), HS),
	nachbar_von( h(_,_,_,_,dunhill), h(_,_,horse,_,_), HS),
	nachbar_von( h(_,_,_,_,blend), h(_,_,cats, _,_), HS),
	nachbar_von( h(_,_,_,_,blend), h(_,_,_,water,_), HS),
	member(  h(_,Owns,zebra,_,_), HS).
