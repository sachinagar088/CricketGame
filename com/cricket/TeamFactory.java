package com.cricket;

public class TeamFactory {

    public Team getTeams(int id){
        Team team = new Team();
        team.setTeamID(id);
        createTeam(team);
        return team;
    }
    public void createTeam(Team team){
        System.out.println("Enter Team "+team.getTeamID()+" Details \n");
        Input input = new Input();
        team.setTeamName(input.getString("Enter Team Name"));
        for(int i=0;i<3;i++){
            Player player = new Player();
            player.setPlayerId(input.getInteger("Enter Player Id"));
            player.setName(input.getString("Enter Player Name"));
            player.setPlayerRole(input.getString("Enter Batsman or Bowler"));
            if(player.getPlayerRole().equals("Bowler")){
                Bowler bowler = new Bowler();
                bowler.setName(player.getName());
                bowler.setPlayerId(player.getPlayerId());
                team.addBowler(bowler);
            }
            team.addPlayer(player);
        }
        team.setTotalWicketsFall(0);
        team.setTeamScore(0);
    }
}
