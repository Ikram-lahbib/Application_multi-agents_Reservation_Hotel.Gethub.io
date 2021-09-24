# Application_multi-agents_Reservation_Hotel.Gethub.io
Dans ce mini projet, On souhaite créer une application multi-agents sert à Développer une Plateforme de réservation d’Hôtel, ce dernier a pour but de réaliser un comparateur de prix dans le cadre d’une Plateforme de Voyage à base de système multi-agent.
Ce SMA est contient:
Agent Interface (AI) qui permet aux clients de saisir sa demande et l’envoie à l’agent AR.
L’agent réservation(AR) devrait choisir l’agent hôtel(AH) qui offre le meilleur prix à travers un processus de communication entre les agents.
Première phase :
• Création de Main Container
• l’enregistrement des agents auprès de SMA et publication de leurs services auprès de l'agent DF.
Deuxième phase :
Les comportements cycliques qui fonctionnent simultanément de l’agent AR:
• Attendre la requête du AI
• Attendre la proposition de l'AH
Troisième phase :
• Communication entre AR et AH (ACCEPT d’un offre et REFUSE d’un autre).
• Confirmation de la part de l’agent AH.
• L’arrêt de tous les agents AH ainsi que le AR.
• Réception de meilleure offre (AI).
