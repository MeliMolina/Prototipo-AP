class CitaController < ApplicationController
        
  before_action :authenticate_medico!
  before_action :set_citum, only: [:show, :edit, :update, :destroy]
  
  # GET /cita
  # GET /cita.json
  def index
      @cita = current_medico.citum.paginate(page: params[:page], :per_page => 25)
  end

  # GET /citas/1
  # GET /citas/1.json
  def show
  end

  # GET /cita/new
  def new
    @citum = Citum.new
  end

  # GET /citas/1/edit
  def edit
  end

  # POST /citas
  # POST /citas.json
  def create
    @citum = Citum.new(citum_params)

    respond_to do |format|
      if @citum.save
        format.html { redirect_to @citum, notice: 'Cita fue creada exitosamente.' }
        format.json { render :show, status: :created, location: @citum }
      else
        format.html { render :new }
        format.json { render json: @citum.errors, status: :unprocessable_entity }
      end
    end
  end

  # PATCH/PUT /citas/1
  # PATCH/PUT /citas/1.json
  def update
    respond_to do |format|
      if @citum.update(citum_params)
        format.html { redirect_to @citum, notice: 'Cita fue actualizada exitosamente.' }
        format.json { render :show, status: :ok, location: @citum }
      else
        format.html { render :edit }
        format.json { render json: @citum.errors, status: :unprocessable_entity }
      end
    end
  end

  # DELETE /citas/1
  # DELETE /citas/1.json
  def destroy
    @citum.destroy
    @citum.pacientes.destroy_all
    respond_to do |format|
      format.html { redirect_to citum_url, notice: 'Cita fue eliminada exitosamente.' }
      format.json { head :no_content }
    end
  end

  private
    # Use callbacks to share common setup or constraints between actions.
    def set_citum
      @citum = Citum.find(params[:id])
      @citum.medico = Medico.find(params[:medico_id, :nombre])
      @citum.paciente = Paciente.find(params[:paciente_id, :nombre])
    end

    # Never trust parameters from the scary internet, only allow the white list through.
    def citum_params
      params.require(:citum).permit(:fecha,:hora,:medico_id,:paciente_id)
    end
end
