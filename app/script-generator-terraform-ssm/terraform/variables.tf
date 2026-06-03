
variable "parametros" {
          type = list(object({
            name             = string
            value            = string
            microservicename = string
          }))
        }
variable profile {
           type = string
         }
