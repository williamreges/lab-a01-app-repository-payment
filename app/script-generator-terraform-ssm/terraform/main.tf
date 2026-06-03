
terraform {
          required_providers {
            aws = {
              source  = "hashicorp/aws"
              version = "~> 5.0"
            }
          }
        }
provider "aws" {
          profile = var.profile
        }
resource "aws_ssm_parameter" "parametersstore" {
          count = length(var.parametros)

          name  = var.parametros[count.index].name
          value = var.parametros[count.index].value
          type  = "String"
        }
