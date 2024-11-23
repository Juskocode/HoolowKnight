[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/rUa5vdmg)

## Descrição do Projeto
O nosso projeto têm em vista extender o projeto do Hero e torná-lo num Platformer em 2D. O jogo inclui mecânicas
que geralmente encontram-se neste tipo de jogo como Dash e Jump. O objetivo é apenas atravessar diversos mapas
sem morrer, face aos diversos inimigos e obstáculos que existem, e chegar ao final.

# Funcionalidades a Implementar no Jogo

## Mecânicas do Jogo
- **Randomização de Níveis**: criação de layouts de nível variados em cada partida.
- **Ação de Saltar**: introdução da mecânica de salto para o jogador.
- **Físicas do Jogo**: implementação de física para elementos móveis e interações.

## Combate
- **Ataques do Jogador**:
    - **Ataque Corpo-a-Corpo** (close range)
    - **Ataque de Longo Alcance** (long range)
    - **Ataque Especial**: habilidade especial com maior dano ou efeito único.

## Coletáveis e Inimigos
- **Coletáveis**: introdução de itens colecionáveis ao longo dos níveis.
- **Tipos de Inimigos**: criação de variedades de inimigos com habilidades distintas.

## Interface e Navegação
- **Menu Principal**: adição de um menu de navegação.
- **Barras de Informação**: visualização de status e informações importantes (vida, energia, etc.).

## Movimentação Avançada
- **Dash ou Dodge**: possibilidade de implementar uma habilidade de desvio rápido.

## Gráficos
- **Ajuste de Resolução**: melhoria e optimização da resolução do jogo.

## Documentação
- **Problemas/Funcionalidades e Padrões**:
  - As funcionalidades já implementadas, ou que futuramente serão implementadas, como o Dash ou o Jump foram escolhidas para dar uma movimentação avançada ao jogador.
  - Alguns padrões de Design de Software usados foram:
    - o Singleton, usado na Game class fazendo com que só possa haver uma única instância da class Game;
    - o MCV (Model-View-Controller);
    - o Game Loop, presente na classe Game no método Start;
    - o State, usado para representar os diferentes estados do Knight;
    