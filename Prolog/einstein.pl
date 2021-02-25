% A steht links neben B
leftof(A, B, [A, B|_]).
leftof(A, B, [_|R]) :- leftof(A, B, R).

% A steht neben B
nextto(A, B, H) :- leftof(A, B, H); leftof(B, A, H).

% A ist das erste Haus
first(A, [A|_]).

% A steht in der Mitte
inthemiddle(A, [_, _, A, _, _]).

% Das Raetsel
einstein :-
  % 5 Haeuser stehen nebeneinander.
  H = [_, _, _, _, _],
  % Der Englaender lebt im roten Haus.
  member([englaender, rot, _, _, _], H),
  % Der Spanier hat einen Hund.
  member([spanier, _, hund, _, _], H),
  % Der Ukrainer trinkt gern Tee.
  member([ukrainer, _, _, tee, _], H),
  % Das gruene Haus ist (direkt) links vom weissen Haus.
  leftof([_, gruen, _, _, _], [_, weiss, _, _, _], H),
  % Im gruenen Haus wird Kaffee getrunken.
  member([_, gruen, _, kaffee, _], H),
  % Die Person, die Old-Gold raucht, hat eine Schnecke.
  member([_, _, schnecke, _, oldgold], H),
  % Der Bewohner des mittleren Hauses trinkt Milch.
  inthemiddle([_, _, _, milch, _], H),
  % Der Bewohner des gelben Hauses raucht Kools.
  member([_, gelb, _, _, kools], H),
  % Der Norweger wohnt im ersten Haus.
  first([norweger, _, _, _, _], H),
  % Der Chesterfields-Raucher wohnt neben der Person mit
  % der Fuchs.
  nextto([_, _, _, _, chesterfields], [_, _, fuchs, _, _], H),
  % Der Mann mit dem Pferd lebt neben der Person, die
  % Kools raucht.
  nextto([_, _, pferd, _, _], [_, _, _, _, kools], H),
  % Der Lucky-Strike-Raucher trinkt Orangensaft.
  member([_, _, _, orangensaft, luckystrike], H),
  % Der Norweger wohnt neben dem blauen Haus.
  nextto([norweger, _, _, _, _], [_, blau, _, _, _], H),
  % Der Japaner raucht Parliaments.
  member([japaner, _, _, _, parliaments], H),
  % Der Chesterfields-Raucher hat einen Nachbarn, der Wasser
  % trinkt.
  nextto([_, _, _, _, chesterfields], [_, _, _, wasser, _], H),
  % Wem gehoert das Zebra?
  member([N, _, zebra, _, _], H),
  write('Der '),
  write(N),
  write('\n'),
  write(H),
  write(' haelt das Zebra.'),
  !.
