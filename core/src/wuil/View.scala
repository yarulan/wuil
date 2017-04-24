package wuil

import htmldsl.Backend

class View {
  implicit val backend: Backend = null
}

class UserForm(name: String, email: String) extends View {
  import htmldsl._
  form(`class` := "form-horizontal", {
    div(`class` := "form-group", {
      val inputId = "email"
      label(`class` := "control-label col-sm-2", `for` := inputId, {
        text("Email")
      })
      div(`class` := "col-sm-10", {
        input(`type` := "email", `class` := "form-control", id := inputId, placeholder := "Enter email")
      })
    })
  })
}
