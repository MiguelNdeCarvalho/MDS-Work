#!/bin/bash

function colors
{
    NOCOLOR='\033[0m'
    RED='\033[0;31m'
    GREEN='\033[0;32m'
    ORANGE='\033[0;33m'
    BLUE='\033[0;34m'
    PURPLE='\033[0;35m'
    CYAN='\033[0;36m'
    LIGHTGRAY='\033[0;37m'
    DARKGRAY='\033[1;30m'
    LIGHTRED='\033[1;31m'
    LIGHTGREEN='\033[1;32m'
    YELLOW='\033[1;33m'
    LIGHTBLUE='\033[1;34m'
    LIGHTPURPLE='\033[1;35m'
    LIGHTCYAN='\033[1;36m'
    WHITE='\033[1;37m'
}

function file_exists
{
    if [ -f "$1" ]; then
        echo -e ""
    else
        echo -e "${RED} JAR não encontrado, compile o programa primeiro! \n ${NOCOLOR}"
        return 1
    fi
}

function folder_exists
{
    if [ -d "$1" ]; then
        echo -e ""
    else
        echo -e "${RED} A pasta target não existe! \n ${NOCOLOR}"
        return 1
    fi
}

function menu
{
    JAR=PresenceManager/target/PresenceManager-1.0-SNAPSHOT-jar-with-dependencies.jar
    TRG=PresenceManager/target/
    
    echo -e "${YELLOW} O que pretende fazer ? ${NOCOLOR}"
    echo -e ""
    echo -e "${YELLOW} 1 - Compilar o Programa \n 2 - Executar o progama \n 3 - Correr Testes \n 4 - Limpar binários compilados \n 5 - Sair ${NOCOLOR}"

    read choice
    case $choice in
            1) mvn clean compile assembly:single -f PresenceManager/pom.xml && echo -e "${GREEN} ✔️ A build foi feita com sucesso ${NOCOLOR} \n" ;;
            2) file_exists "${JAR}" && java -jar "${JAR}" && echo -e "";;
            3) mvn test -f PresenceManager/pom.xml && echo -e "";;
            4) folder_exists "${TRG}" && mvn clean -f PresenceManager/pom.xml && echo -e "";;
            5) exit;;
            *) echo -e "${RED}Opção inválida! \n ${NOCOLOR}";;
    esac
}

function main()
{
    colors
    while true
    do
        menu
    done        
}

main