//  create a model for the hackathon

export  class Hackathon {
  constructor(public hackathonId?: number, public name?: string, public theme?: string,
    public registrationStartDate?: string,
    public registrationEndDate?: string,
    public eventDate?: string,
    public maxTeamSize?: number,
    public maxTeams?: number,
    public challengeTitles?: string[]
    ) {}

}


