2.	Descreva quais são as principais limitações ao se adotar servidores de aplicação em uma arquitetura orientada a microsserviços. 

	

3.	Quais são os principais desafios ao se adotar aplicações do tipo "Embedded Servlet” em relação a aplicações desenvolvidas para um application server?

	Cada microserviço deve possuir um servidor web e um banco de dados independente, para funcionar de manteira isolada do restante da aplicação, que seja escalável e tolerante à falhas.
	Um application server além de utilizar mais recursos de hardware, não seria capaz de escalonar e gerenciar microsserviços de forma individuais, dificultando a manutenção e a customização de memória/processamento para cada microserviço, inviabilizando sua aplicação. 
