export class Job {
  name: string;
  displayName: string;
  description: string

  constructor(name: string, displayName: string, description: string) {
    this.displayName = description
    this.name = name
    this.description = description
  }
}
