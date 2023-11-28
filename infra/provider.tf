terraform {
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "4.67.0"
    }
  }
  backend "s3" {
    bucket = "pgr301-2021-terraform-state"
    key    = "candidate-2042/apprunner-a-new-state.state"
    region = "eu-north-1"
  }
}