<#import "spring.ftl" as spring />

  <form name="loginForm" method="POST" action="j_acegi_security_check" >
  <tr>
    <td style="width: 200px; height: 320px;">
      <table>
        <tr>
          <td><img src="img/arrow-small.png"/></td>
          <td>Sua identificação é necessária a partir deste ponto.</td>
        </tr>
        <tr>
          <td>&#160;</td>
          <td><a href="privacy.htm">Privacidade</a></td>
        </tr>
      </table>
    </td>
    <td style="background-image: url(img/arrow-bg.png);">
      <table style="width: 200px;">
        <tr>
          <td>Usuário: </td>
          <td><input type="text" name="j_username" size="16"/></td>
        </tr>
        <tr>
          <td>Senha: </td>
          <td><input type="password" name="j_password" size="10"/></td>
        </tr>
        <tr colspan="2">
          <td>
            <input type="submit" alignment="center" value="Entrar" class="btn">
          </td>
        </tr>
      </table>
    </td>
    <td style="width: 160px;">
      <table>
        <tr>
          <td><img src="img/ask.png"/></td>
          <td><a href="credential.htm">Não possuo senha</a></td>
        </tr>
        <tr>
          <td>&#160;</td>
          <td><a href="remember.htm">Esqueci minha senha</a></td>
        </tr>
      </table>
    </td>
  </tr>
  </form>
