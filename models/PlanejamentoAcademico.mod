#conjuntos
set DISCI; 	# DISCI - C
set SALA; 	# SALA - S
set PROF; 	# PROF - P
set COMBO; 	# COMBO - B
set HORARIO; 	# HORARIO - H


param MINHp {PROF} >0;
param MAXHp {PROF} >0;
param z {PROF,DISCI,COMBO};
param Nc {DISCI};

var X {DISCI, SALA, PROF, COMBO, HORARIO} binary; 

#FUNÇÃO OBJETIVO 
# TODO: Corrigir utilidade para professor e disciplina
maximize f:sum {c in  DISCI, s in SALA, p in PROF,b in COMBO, h in HORARIO} X[c,s,p,b,h] * z[p,c,b];

#RESTRIÇOES 
# Cada professor tem uma carga horária mínima
subject to MinimoHorario {p in PROF}:
sum {c in DISCI, s in SALA, b in COMBO, h in HORARIO} X[c,s,p,b,h] * Nc[c] >= MINHp[p];

# Cada professor tem uma carga horária máxima
subject to MaximoHorario {p in PROF}:
sum {c in DISCI, s in SALA, b in COMBO, h in HORARIO} X[c,s,p,b,h] * Nc[c] <= MAXHp[p];

# Compatibilidade entre professor e discipĺina
subject to DisciplinaProfessor {c in DISCI}:
sum {s in SALA, p in PROF, b in COMBO, h in HORARIO} X[c,s,p,b,h] = 1;

# Compatibilidade entre discipĺina e horários
subject to DisciplinaHorario {h in HORARIO, c in  DISCI}:
sum {s in SALA, p in PROF, b in COMBO} X[c,s,p,b,h] = 1;

# Compatibilidade entre salas e discipĺina
subject to DisciplinaSala {c in DISCI, s in SALA}:
sum {p in PROF, b in COMBO, h in HORARIO} X[c,s,p,b,h] = 1;

# TODO: Compatibilidade entre combos e disciplinas


# TODO:  Identificação das validações primárias 
	# Erros primários
# TODO: Identificação das validações secundárias (totalizações)
	# Erros secundários
	# Warnings secundários
